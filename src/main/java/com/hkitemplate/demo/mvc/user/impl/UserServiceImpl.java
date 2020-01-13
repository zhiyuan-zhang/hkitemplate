package com.hkitemplate.demo.mvc.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.exceptions.CheckException;
import com.hkitemplate.demo.mvc.user.User;
import com.hkitemplate.demo.mvc.user.mapper.UserMapper;
import com.hkitemplate.demo.mvc.user.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkitemplate.demo.utils.ExcelManage;
import com.hkitemplate.demo.utils.FileUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanghao
 * @since 2019-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<User> findProfessor(Page page, List<Integer> levels, Integer memberCategory, Integer polling, Integer status, Integer tableId) {
        return page.setRecords(this.baseMapper.findProfessor(page,levels,memberCategory,1,1,tableId) );
    }

    /**
     * 文件打包压缩工具
     * @param stringList
     * @return
     */
    public String zip(List<String> stringList){
        String companyInfoExcelFilePath = "";
        String shareholderListExcelFilePath = "";
        List<String> fileList = Arrays.asList(companyInfoExcelFilePath,shareholderListExcelFilePath);
        if (ObjectUtils.isEmpty(fileList)) {
            throw new CheckException("当前表单下没有文件");
        }
        int id = RandomUtils.nextInt();
        String zipPath = id + "-资料压缩包.zip";
        String zipFtpUrl = null;
        FileUtil.ZipFiles(fileList, zipPath);
        return zipFtpUrl;
    }


    public String companyInfoExcel(List<String> ids){

        List<User> userList =
                this.list(new QueryWrapper<User>().lambda().in(User::getId,
                        ids));




        String basePath = "/Users/apple/Desktop/doc/";

        String tmPath = basePath + "模板表.xls";


        // 判断文件是否存在
        if (!ExcelManage.fileExist(tmPath)) {

        }

        // 生成地址
        String filePath = basePath + "生成后的模板.xls";
        int startRow = 1;

        // 创建地址
        ExcelManage.createExcel(tmPath, filePath);


        ArrayList<String> title = new ArrayList<>(Arrays.asList( "id","name","age","phone"));

        try {
            for (int i = 0; i < userList.size(); i++) {
                ExcelManage.writeToExcel(filePath, "Sheet1", userList.get(i), title, i + startRow);
            }

        } catch (NoSuchMethodException e) {
            // 方法和title无法对于
            throw new CheckException("生成excel失败");
        } catch (IOException e) {
            //反射异常
            throw new CheckException("生成excel失败");
        }

        return filePath;
    }



}
