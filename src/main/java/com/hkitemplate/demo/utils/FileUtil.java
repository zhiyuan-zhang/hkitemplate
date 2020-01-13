package com.hkitemplate.demo.utils;

import com.common.exceptions.FileStorageException;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil extends FileUtils {

	public static void main(String arg[]){
		try {
			File file = new File("/Users/apple/Desktop/Workflow");
			File[] srcfile = {file};

			int i = ZipFiles(srcfile,"/Users/apple/Desktop/doc/test.zip");
			System.out.println("invoke end: "+i);
		}catch (Exception e){

		}
	}


	/**
	 * 压缩文件
	 *
	 * @param srcfile
	 *            File[] 需要压缩的文件列表
	//	 * @param zipfile
	 *            File 压缩后的文件
	 * @throws Exception
	 */
	public static int ZipFiles(File[] srcfile, String zipFile) throws FileStorageException {
		try {

			// Create the ZIP file
//			if (!FileUtil.isExist(zipFile)) {
//				FileUtil.createFile(zipFile);
//			}

			OutputStream os = new FileOutputStream(zipFile);
			BufferedOutputStream bs = new BufferedOutputStream(os);
			ZipOutputStream out = new ZipOutputStream(bs);
			// Compress the files
			for (int i = 0; i < srcfile.length; i++) {
				zip(srcfile[i], new File(zipFile), out, true, true);
			}
			out.closeEntry();
			// Complete the ZIP file
			out.close();
			System.out.println("压缩完成.");
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 压缩文件
	 *
	 * @param srcfile
	 *            File[] 需要压缩的文件列表
	//	 * @param zipfile
	 *            File 压缩后的文件
	 * @throws Exception
	 */
	public static int ZipFiles(List<String> srcfile, String zipFile) throws FileStorageException {
		try {

			// Create the ZIP file
//			if (!FileUtil.isExist(zipFile)) {
//				FileUtil.createFile(zipFile);
//			}

			OutputStream os = new FileOutputStream(zipFile);
			BufferedOutputStream bs = new BufferedOutputStream(os);
			ZipOutputStream out = new ZipOutputStream(bs);
			// Compress the files
			srcfile.forEach( i ->{
				try {
					zip(new File(i), new File(zipFile), out, true, true);
				} catch (IOException e) {
					e.printStackTrace();
				}


			});


			out.closeEntry();
			// Complete the ZIP file
			out.close();
			System.out.println("压缩完成.");
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}



	/**
//	 * @param path 要压缩的路径, 可以是目录, 也可以是文件.
	 * @param basePath 如果path是目录,它一般为new File(path), 作用是:使输出的zip文件以此目录为根目录, 如果为null它只压缩文件, 不解压目录.
	 * @param zo 压缩输出流
	 * @param isRecursive 是否递归
	 * @param isOutBlankDir 是否输出空目录, 要使输出空目录为true,同时baseFile不为null.
	 * @throws IOException
	 */
	public static void zip(File inFile, File basePath, ZipOutputStream zo, boolean isRecursive, boolean isOutBlankDir) throws IOException {
		File[] files = new File[0];
		if(inFile.isDirectory()) {    //是目录
			files = inFile.listFiles();
		} else if(inFile.isFile()) {    //是文件
			files = new File[1];
			files[0] = inFile;
		}
		byte[] buf = new byte[1024];
		int len;
		System.out.println("baseFile: "+basePath.getPath());
		for(int i=0; i<files.length; i++) {
			String pathName = "";
			if(basePath != null) {
				if(basePath.isDirectory()) {
					pathName = files[i].getPath().substring(basePath.getPath().length()+1);
				} else {//文件
//					pathName = files[i].getPath().substring(basePath.getParent().length()+1);
					pathName = inFile.getName();
				}
			} else {
				pathName = files[i].getName();
			}
			if(files[i].isDirectory()) {
				if(isOutBlankDir && basePath != null) {

					ZipEntry zipEntry = new ZipEntry(pathName + "/");

					zo.putNextEntry(zipEntry);    //可以使空目录也放进去
				}
				if(isRecursive) {    //递归
					zip(files[i], basePath, zo, isRecursive, isOutBlankDir);
				}
			} else {
				FileInputStream fin = new FileInputStream(files[i]);
				zo.putNextEntry(new ZipEntry(pathName));
				while((len=fin.read(buf))>0) {
					zo.write(buf,0,len);
				}
				fin.close();
			}
		}
	}
	public static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

	public static void downloadFile(String url, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		if (!StringUtils.isEmpty(url)) {
			String name = url.substring(url.lastIndexOf("/") + 1);
			String name1 = name.substring(name.indexOf("-") + 1);
			File file = new File(url);

			boolean msBrowser;
			String userAgent = request.getHeader("User-Agent");
			for (String signal : IEBrowserSignals) {
				if (userAgent.contains(signal)) {
					msBrowser = true;
				}
			}
			msBrowser = false;


			if (msBrowser) {
				// 设置文件编码
				name1 = URLEncoder.encode(name1, "UTF-8");
			} else {
				//万能乱码问题解决
				name1 = new String(name1.getBytes("UTF-8"), "ISO-8859-1");
			}
			response.setCharacterEncoding("UTF-8");

			if (file.exists()) {
				//response.setContentType("multipart/form-data");
				response.setContentType("application/force-download");
				response.addHeader("Content-Length", "" + file.length());
				response.setHeader("Content-Disposition", "attachment;fileName=" + name1);
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;

				OutputStream os = null;
				try {
					os = response.getOutputStream();
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer);
						i = bis.read(buffer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
