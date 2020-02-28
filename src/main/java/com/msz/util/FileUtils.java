package com.msz.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * 文件操作相关的工具包
 */
@Slf4j
public class FileUtils {
    
    /**
     * 获取所有包的所有资源文件，支持表达式
     * @param locationPattern 例如 ： classpath:spring/*.xml 按优先级加载 classpath*:spring/*.xml 对所有jar扫描
     * @return the corresponding Resource objects
     * @throws IOException in case of I/O errors
     */
    public static Resource[] getResources(String locationPattern) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(locationPattern);
        return resources;
    }
    
    /**
     * 获取所有包的所有资源文件，支持表达式
     * @param locationPattern 例如 ： classpath:spring/*.xml 按优先级加载 classpath*:spring/*.xml 对所有jar扫描
     * @return the corresponding Resource object
     * @throws IOException in case of I/O errors
     */
    public static Resource getResource(String locationPattern) throws IOException {
        Resource[] resources = getResources(locationPattern);
        if (resources.length > 0) {
            return resources[0];
        } else {
            return null;
        }
    }
    
    /**
     * 获取文件夹下所有的文件列表
     * @param directory 文件目录
     * @return 目录下所有的文件列表
     */
    public static List<File> findDirectoryFiles(File directory) {
        List<File> files = new ArrayList<File>();
        if (directory.isDirectory()) {
            File[] dirFiles = directory.listFiles();
            for (int i = 0; i < dirFiles.length; i++) {
                File file = dirFiles[i];
                if (file.isDirectory()) {
                    files.addAll(findDirectoryFiles(file));
                } else {
                    files.add(file);
                }
            }
        } else {
            files.add(directory);
        }
        return files;
    }
    
    /**
     * 文件上传方法
     * @param f 文件
     * @param fFileName 文件名，含扩展名。若为null或空字符串，则取f.getName()。如果f为临时文件请务必提供该参数
     * @param uploadDir 上传目录的绝对路径
     * @param maxSize 附件最大容量支持（单位KB）
     * @return 返回上传后的文件名，不含任何目录路径
     */
    public static String uploadFile(File f, String fFileName, String uploadDir, int maxSize) {
        if (null == f) {
            return null;
        }
        fFileName = isNullOrEmpty(fFileName) ? f.getName() : fFileName;
        String newFileName = null;
        // 生成短MD5加密+历史毫秒数+100内随机数的新文件名
        String randomName = MD5(fFileName) + System.currentTimeMillis() + new DecimalFormat("00").format(new Random().nextInt(100));
        int index = fFileName.lastIndexOf('.');
        File dir = new File(uploadDir);
        // 如果目录不存在则创建它
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 判断文件是否包含扩展名
        if (index != -1) {
            newFileName = randomName + fFileName.substring(index);
        } else {
            newFileName = randomName.toString();
        }
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream(new File(dir, newFileName));
            bos = new BufferedOutputStream(fos);
            byte[] buf = new byte[maxSize * 1024];
            int len = -1;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFileName;
    }
    
    /**
     * 图片文件等比缩略上传方法
     * @param imageFile 需要缩略的图片文件
     * @param zoomPath 缩略后的绝对保存路径
     * @param width 最大宽度，0则使用默认最大宽度-300
     * @param height 最大高度，0则使用默认最大高度-300
     * @return 返回true表示缩略保存成功
     */
    public static boolean zoomImage(File imageFile, String zoomPath, int width, int height) {
        try {
            if (!imageFile.isFile()) {
                return false;
            }
            Image image= Toolkit.getDefaultToolkit().getImage(imageFile.getPath());
            BufferedImage srcImg=ImageUtil.toBufferedImage(image);
//            srcImg = ImageIO.read(imageFile);
            if (null == srcImg) {
                return false;
            }
            double ratio = 0.0;
            width = width == 0 ? 300 : width;
            height = height == 0 ? 300 : height;
            // 当原图尺寸小于压缩尺寸时，使用原图本身尺寸
            if (srcImg.getHeight() <= height && srcImg.getWidth() <= width) {
                width = srcImg.getWidth();
                height = srcImg.getHeight();
            } else {
                if (srcImg.getHeight() > srcImg.getWidth()) {
                    ratio = (double) height / srcImg.getHeight();
                } else {
                    ratio = (double) width / srcImg.getWidth();
                }
                height = (int) (srcImg.getHeight() * ratio);
                width = (int) (srcImg.getWidth() * ratio);
            }
            BufferedImage filterImage = new BufferedImage(width, height, srcImg.getType());
            filterImage.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            File zoomFile = new File(zoomPath);
            String suffix = zoomFile.getName().substring(zoomFile.getName().lastIndexOf('.') + 1);
            zoomFile.mkdirs();
            ImageIO.write(filterImage, suffix, zoomFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.isEmpty() || (str.trim().isEmpty())) {
            return true;
        }
        return false;
    }
    
    public static String MD5(String plainText) {
        StringBuffer buf = new StringBuffer();
        plainText = plainText == null ? "" : plainText.trim();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
    
    /**
     * 获取16位的随机字符串
     * @return
     * @author wuling
     */
    public static String randomString(int length) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] charArr = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            charArr[i] = str.charAt(random.nextInt(str.length()));
        }
        return new String(charArr);
    }

	/**
	 * 
	 * @Description 上传文件
	 * @param file File对象
	 * @param folder 存入的文件夹
	 * @param allowExt 文件后缀名
	 * @param allowSize 允许上传的文件大小
	 * @return FileReturnBean
	 */
	@SuppressWarnings("deprecation")
	public static FileReturnBean uploadConFile(File file, String folder,
			String allowExt, long allowSize) {
		FileReturnBean fileReturnBean = null;
		try {
			if(folder==null || folder.trim().length()==0){
				folder = "default";
			}
			fileReturnBean = new FileReturnBean();

			// 获得源文件
			if (!file.exists()) {
				fileReturnBean.setCode(1001); // 失败
				fileReturnBean.setMessage("file对象找到不到!");
				fileReturnBean.setReturnValue(null);
				return fileReturnBean;
			}
			// 得到文件名
//			String name = file.getPath().substring(
//					file.getPath().lastIndexOf("\\") + 1,
//					file.getPath().lastIndexOf("."));
			//验证文件格式 和大小
			if(file.length() <= allowSize){
				String outputDir ="C:/workspace/bcw/src/main/webapp/static/contractfile";
				Date date = new Date();
				String outFolder = "/" + folder + "/" +date.getYear()+""+(date.getMonth() + 1)+""+date.getDate();
				//String outFolder = "/" + folder;
				// 判断文件夹是否存在
				File fileOu = new File(outputDir + outFolder);
				if (!fileOu.exists()) {
					fileOu.mkdirs(); // 不存在进行创建
				}
				
				int num = (int) (Math.random() * 1000000); // 6位随机数

				String yuanFileName =  outFolder + "/" + date.getYear() + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + date.getHours()+ "_" +date.getMinutes()+ "_" +date.getSeconds() + "_"+ date.getTime() +"_" + num + "_1." + allowExt;
				String yuanPath = yuanFileName;
				File outFile = new File(outputDir + yuanPath);
				// 创建流文件读入与写出类
				FileInputStream inStream = new FileInputStream(file);
				FileOutputStream outStream = new FileOutputStream(outFile);
				// 通过available方法取得流的最大字符数
				byte[] inOutb = new byte[inStream.available()];
				inStream.read(inOutb); // 读入流,保存在byte数组
				outStream.write(inOutb); // 写出流,保存在文件newFace.gif中
				inStream.close();
				outStream.close();

				fileReturnBean.setCode(1000); // 成功
				fileReturnBean.setMessage("上传成功!");
				fileReturnBean.setReturnValue(yuanPath); // 存入路径地址
			}else{
				fileReturnBean.setCode(1001); // 失败
				fileReturnBean.setMessage("文件超出大小限制!");
				fileReturnBean.setReturnValue(null); // 存入路径地址
			}			

		} catch (Exception e) {
			e.printStackTrace();
			fileReturnBean.setCode(1001); // 成功
			fileReturnBean.setMessage("上传失败!");
			fileReturnBean.setReturnValue(null);
            log.info("上传文件失败!");
        }
		return fileReturnBean;
	}
	
	/**
	 * 存储文件
	 * @param stream
	 * @param tagFileName 存储路径
	 * @throws IOException
	 */
	public static void SaveFileFromInputStream(InputStream stream,String tagFileName) throws IOException    
	{      
		File outfile=new File(tagFileName);
		OutputStream os=null;
		try{
			os=new FileOutputStream(outfile);
			byte buffer[]=new byte[4*1024];
			int len=0;
			while((len=stream.read(buffer))!=-1){
				os.write(buffer,0,len);
			}
			os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				os.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
    
    
    
    
    
    
}
