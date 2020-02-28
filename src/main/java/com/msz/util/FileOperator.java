package com.msz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: lzm
 * @CreateTime: 2019/1/22
 * @Describe: 文件操作工具大集合
 */
@Slf4j
public class FileOperator {

	/**
	 * 判断文件夹是否存在
	 *
	 * @param folderPath
	 */
	public static void folderIsExist(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.exists()) {
			//	开启可写权限
			folder.setWritable(true);
			folder.mkdirs(); // 不存在进行创建
		}
	}

	/**
	 * 文件下载
	 * @param pdfPath
	 * @param pdfName
	 * @param response
	 * @throws IOException
	 */
	public static void downLoadFile(String pdfPath, String pdfName, HttpServletResponse response) throws IOException {

		File f = new File(pdfPath + "/" + pdfName);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		/*
		 * if (isOnLine) { // 在线打开方式 URL u = new URL("file:///" + filePath);
		 * response.setContentType(u.openConnection().getContentType());
		 * response.setHeader("Content-Disposition", "inline; filename=" +
		 * f.getName()); // 文件名应该编码成UTF-8 } else { // 纯下载方式
		 */
		response.setContentType("application/x-msdownload");
		//下载时必须设置字符编码，不然无法显示中文名
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getName(),"UTF-8"));
		// }
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
		
		
	}
	
	/**
	 * 下载文件
	 * @param filePathName
	 * @param response
	 * @throws IOException
	 */
	public static void downLoadFile(String filePathName, HttpServletResponse response){
		File f = new File(filePathName);
		if (!f.exists()) {
			try {
				response.sendError(404, "File not found!");
			} catch (IOException e) {
				log.info("response响应错误提示失败:{}",e);
				e.printStackTrace();
			}
			return;
		}
		try{
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset(); // 非常重要
			response.setContentType("application/x-msdownload");
			try{
				//下载时必须设置字符编码，不然无法显示中文名
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getName(),"UTF-8"));
				try{
					OutputStream out = response.getOutputStream();
					while ((len = br.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					br.close();
					out.close();
					log.info("文件:{},下载成功!",filePathName);
				}catch (IOException e){
					log.info("文件:{},IO异常!",filePathName);
				}
			}catch (UnsupportedEncodingException e){
				log.info("文件:{},编码不支持!",filePathName);
			}
		}catch(FileNotFoundException e){
			log.info("文件:{},找不到!",filePathName);
		}
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String pdfPath,String fileName) {
		File file = new File(pdfPath+ "/" + fileName);
		if (!file.exists()) {
			log.info("删除文件失败:" + pdfPath+ "/" +  fileName + "不存在！");
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(pdfPath+ "/" +fileName);
			} else {
				return deleteDirectory(pdfPath+ "/" +fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				log.info("删除单个文件:" + fileName + "==成功！");
				return true;
			} else {
				log.info("删除单个文件:" + fileName + "==失败！");
				return false;
			}
		} else {
			log.info("删除单个文件失败：" + fileName + "==不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileOperator.deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = FileOperator.deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 重命名文件
	 *
	 * @param pdfPath
	 * @param oldFile
	 * @param toFile
	 */
	public static void renameFile(String pdfPath,String oldFile,String toFile){
		
		String ofile = pdfPath+"/"+oldFile;
		String nfile = pdfPath+"/"+toFile; 
		
        File toBeRenamed = new File(ofile);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {

            System.out.println("File does not exist: " + ofile);
            return;
        }

        File newFile = new File(nfile);

        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            System.out.println("File has been renamed.");
        } else {
            System.out.println("Error renmaing file");
        }
	}

	/**
	 * 以流形式保存Excel文件
	 *
	 * @param wb
	 * @param filePathName
	 */
	public static void saveExcelFileAsStream(HSSFWorkbook wb, String filePathName) {
		try{
			FileOutputStream fout = new FileOutputStream(filePathName);
			try {
				wb.write(fout);
				fout.close();
			}catch(IOException e){
				log.info("文件：{},IO异常!",filePathName);
				e.printStackTrace();
			}
		}catch(FileNotFoundException e){
			log.info("文件：{}未找到!",filePathName);
			e.printStackTrace();
		}
	}

	/**
	 * 魔数校验(类型待完善)
	 * 判断是否是图片文件
	 *
	 * @param tmpFile
	 * @return
	 * @throws IOException
	 */
	public static boolean isSecImage(MultipartFile tmpFile) throws IOException {
		FileType fileType = getType(tmpFile.getInputStream());
		if (fileType == FileType.JPEG || fileType == FileType.PNG
				|| fileType == FileType.GIF
				|| fileType == FileType.BMP
				|| fileType == FileType.TIFF
				|| fileType == FileType.PSD) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 魔数校验文件类型(安全性校验)
	 * 根据文件流判断文件类型
	 *
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static FileType getType(InputStream inputStream) throws IOException {
		FileType fileType = null;
		byte[] b = new byte[28];
		try {
			inputStream.read(b, 0, 28);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		String fileHead = bytesToHex(b);
		if (fileHead != null && fileHead.length() > 0) {
			fileHead = fileHead.toUpperCase();
			FileType[] fileTypes = FileType.values();

			for (FileType type : fileTypes) {
				if (fileHead.startsWith(type.getValue())) {
					fileType = type;
					break;
				}
			}
		}
		return fileType;
	}


	/**
	 * 判断文件类型,做魔数校验,防文件上传攻击
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static FileType getType(String filePath) throws IOException {
		// 获取文件头
		String fileHead = getFileHeader(filePath);
		if (fileHead != null && fileHead.length() > 0) {
			fileHead = fileHead.toUpperCase();
			FileType[] fileTypes = FileType.values();

			for (FileType type : fileTypes) {
				if (fileHead.startsWith(type.getValue())) {
					return type;
				}
			}
		}
		return null;
	}

	/**
	 * 读取文件头
	 */
	private static String getFileHeader(String filePath) throws IOException {
		byte[] b = new byte[28];
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			inputStream.read(b, 0, 28);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return bytesToHex(b);
	}

	/**
	 * 将字节数组转换成16进制字符串
	 *
	 * @param src
	 * @return
	 */
	public static String bytesToHex(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}



}
