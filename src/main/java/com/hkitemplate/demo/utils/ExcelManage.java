package com.hkitemplate.demo.utils;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author 21  * @date 创建时间：2016年12月8日下午2:38:47
 * @version 1.0
 * @Description:
 */
@Slf4j
public class ExcelManage {

    private static HSSFWorkbook workbook = null;

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean fileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断文件的sheet是否存在
     *
     * @param filePath  文件路径
     * @param sheetName 表格索引名
     * @return
     */
    public static boolean sheetExist(String filePath, String sheetName) {
        boolean flag = false;
        File file = new File(filePath);
        if (file.exists()) {    //文件存在
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if (sheet != null){
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {    //文件不存在
            flag = false;
        }
        return flag;
    }

    /**
     * 创建新Sheet并写入第一行数据
     *
     * @param filePath excel的路径
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createSheet(String filePath) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        // 读取文件
        File excel = new File(filePath);
        // 转换为流
        FileInputStream in = new FileInputStream(excel);

        // 加载excel的 工作目录
        workbook = new HSSFWorkbook(in);
        //添加表头
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param templatePath
     * @param targetPath
     */
    public static void createExcel(String templatePath, String targetPath) {
        //新建文件
        FileOutputStream out = null;
        try {
            InputStream inputStream = new FileInputStream(new File(templatePath));

            HSSFWorkbook tmworkbook = new HSSFWorkbook(inputStream);
            workbook = tmworkbook;

            out = new FileOutputStream(targetPath);

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件.
     *
     * @param filePath 文件路径
     */
    public boolean deleteExcel(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) {
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 往excel中写入.
     * @param filePath 文件路径
     * @param sheetName 表格sheet名称
     * @param object  写入对象
     * @param titleRow  标题list
     * @param startRow  起始行
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void writeToExcel(String filePath, String sheetName, Object object, ArrayList<String> titleRow,
                                    int startRow) throws IOException, NoSuchMethodException{

        //创建workbook
        File file = new File(filePath);

        @Cleanup
        FileInputStream fileInputStream = new FileInputStream(file);

        workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheet(sheetName);
        // 获取表格的总行数

        Row row = sheet.createRow(startRow);     //最新要添加的一行
        //通过反射获得object的字段,对应表头插入
        //获取该对象的class对象
        Class<? extends Object> class_ = object.getClass();

        for (int i = 0; i < titleRow.size(); i++) {
            String title = titleRow.get(i);
            try {
                String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1); // 使其首字母大写;
                String methodName = "get" + UTitle;
                Method method = class_.getDeclaredMethod(methodName); // 设置要执行的方法
                String data = getData(object, method); // 执行该get方法,即要插入的数据
                Cell cell = row.createCell(i);
                cell.setCellValue(data);
            }catch (NullPointerException e){
                log.error("获取excel 数据报错 数据是 : {} ", title);
            }
        }
        @Cleanup
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);

    }

    public static String getData(Object object, Method method) {
        String s = "";

        try {
            s = method.invoke(object).toString();

        } catch (Exception e) {
            log.error("获取excel 数据报错, 已经赋值为空 ! ");
        }
        return s;
    }
}
