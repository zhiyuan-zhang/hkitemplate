package com.hkitemplate.demo;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-09-06 14:11
 * @Description:
 */
public class test {



    public static void main(String[] args) throws Exception {
        String outPath = "/Users/apple/Desktop/压力测试报告.docx";
        wordConverterToPdf(outPath, outPath.replace("docx", "pdf"), "STSong-Light", BaseFont.IDENTITY_H);
    }

    /**
     * 将word文档， 转换成pdf
     * 宋体：STSong-Light
     *
     * @param fontParam1 可以字体的路径，也可以是itextasian-1.5.2.jar提供的字体，比如宋体"STSong-Light"
     * @param fontParam2 和fontParam2对应，fontParam1为路径时，fontParam2=BaseFont.IDENTITY_H，为itextasian-1.5.2.jar提供的字体时，fontParam2="UniGB-UCS2-H"
     * @param tmp        源为word文档， 必须为docx文档
     * @param target     目标输出
     * @throws Exception
     */
    public static void wordConverterToPdf(String tmp, String target, String fontParam1, String fontParam2) {
        InputStream sourceStream = null;
        OutputStream targetStream = null;
        XWPFDocument doc = null;
        try {
            sourceStream = new FileInputStream(tmp);
            targetStream = new FileOutputStream(target);
            doc = new XWPFDocument(sourceStream);
            PdfOptions options = PdfOptions.create();
            //中文字体处理
            // 中文字体处理
            options.fontProvider(new IFontProvider() {

                @Override
                public Font getFont(String familyName, String encoding, float size, int style, java.awt.Color color) {
                    try {
                        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                        Font fontChinese = new Font(bfChinese, 12, style, color);
                        if (familyName != null)
                            fontChinese.setFamily(familyName);
                        return fontChinese;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
//            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 中文、12、正常

            PdfConverter.getInstance().convert(doc, targetStream, options);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            IOUtils.closeQuietly(targetStream);
            IOUtils.closeQuietly(sourceStream);
        }
    }


    @Test
    public void writeV2007WithTemplate() throws IOException {
//        InputStream inputStream = new FileInputStream( new File("/Users/apple/Desktop/doc/eim/公司董监高人员名单及持股情况（股份系统）.xls"));
        String docPath = "/Users/apple/Desktop/压力测试报告.docx";
        String pdfPath = "/Users/apple/Desktop/压力测试报告.pdf";

        InputStream in = new FileInputStream(new File(docPath));
        XWPFDocument document = new XWPFDocument(in);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(new File(pdfPath));
        PdfConverter.getInstance().convert(document, out, options);

        in.close();
        out.close();
    }

}
