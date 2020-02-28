package com.msz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * 图片保存工具类
 */
@Slf4j
public class ImageUtil {

    public static String saveOriginalImage(byte[] fileData, String savePath, String absolutelyContextPath) throws Exception {
        String hashedName = DigestUtils.md5Hex(fileData);
        // 图片的绝对路径+名称
        String picSavePath = absolutelyContextPath + savePath + File.separator + hashedName + ".jpg";
        log.info("picSavePath = " + picSavePath);
        File saveFile = new File(picSavePath);
        // 先保存原图
        FileUtils.writeByteArrayToFile(saveFile, fileData);
        return savePath + File.separator + hashedName + ".jpg";
    }

    public static String saveOriginalImage(byte[] fileData, String savePath, String absolutelyContextPath,String suffix) throws Exception {
        String hashedName = DigestUtils.md5Hex(fileData);
        // 图片的绝对路径+名称
        String picSavePath = absolutelyContextPath + savePath + File.separator + hashedName + suffix;
        log.info("picSavePath = " + picSavePath);
        File saveFile = new File(picSavePath);
        // 先保存原图
        FileUtils.writeByteArrayToFile(saveFile, fileData);
        return savePath + File.separator + hashedName + suffix;
    }

    public static String saveImageForExtensionName(byte[] fileData, String savePath, String suffix) throws Exception {
        // 1. 根据文件数据获取32位的‘十六进制字符串’
        String hashedName = DigestUtils.md5Hex(fileData);
        // 1.1 根据'文件数据byte[]'获取'根据Md5加密后的十六进制字符',截取‘前八位’作为文件保存的文件夹目录
        String basePath = File.separator + hashedName.substring(0, 8);
        String picPath = basePath + File.separator + hashedName;
        FileUtils.forceMkdir(new File(savePath + basePath));
        // 1.2 带保存的图片的完全硬盘路径
        String picSavePath = savePath + picPath + suffix;
        log.info("图片保存路径的完全路径；picSavePath = " + picSavePath);
        File saveFile = new File(picSavePath);
        // 1.3 将文件数据byte[], 写入文件targetFile中
        FileUtils.writeByteArrayToFile(saveFile, fileData);
        return picSavePath;
    }
    
    /**
     * 保存图片文件
     * @param fileData
     * @param savePath -保存图片目录在项目下的相对路径
     * @param absolutelyContextPath -当前项目的绝对路径
     * @return -相对路径+完整图片名称
     * @throws Exception
     * @author LaiZhengming
     */
    
    public static String saveImage(byte[] fileData, String savePath, String absolutelyContextPath) throws Exception {
        String hashedName = DigestUtils.md5Hex(fileData);
        // 图片的绝对路径+名称
        String picSavePath = absolutelyContextPath + savePath + hashedName + ".jpg";
        log.info("picSavePath = " + picSavePath);
        File saveFile = new File(picSavePath);
        // 先保存原图-缩放-删除原图
        FileUtils.writeByteArrayToFile(saveFile, fileData);
        // 缩放
        String smallPicName = DigestUtils.md5Hex(fileData) + ".jpg";
        resizeImage(saveFile, 800, 800, smallPicName, absolutelyContextPath + savePath);
        log.info("start resize success!");
        // 删除
        //saveFile.delete();
        return savePath  + smallPicName;//+ File.separator
    }
    
    /**
     * 缩放图片
     * @param targetFile 源文件
     * @param width
     * @param height
     * @param filename 缩放后图片文件的名称(包含扩展名，如:.jpg)
     * @param outputFolder
     * @throws Exception
     * @author LaiZhengming
     */
    private static void resizeImage(File targetFile, int width, int height, String filename, String outputFolder) throws Exception {
        try {
            if (targetFile.exists()){
                log.info("targetFile是实际存在的文件 路径=" + targetFile.getPath() + "大小 =" + targetFile.length());
            }
            ResizeImage resizeImage = new ResizeImage();
            Image src=Toolkit.getDefaultToolkit().getImage(targetFile.getPath());
            BufferedImage bi=ImageUtil.toBufferedImage(src);
            //            BufferedImage bi = ImageIO.read(targetFile);
            BufferedImage bufferedImage = resizeImage.zoomImage(bi, width, height);
            if (bi != null) {
                log.info("图片转化成功");
            }
            if (bufferedImage != null) {
                log.info("图片缩小成功");
            }
            resizeImage.writeHighQuality(filename, bufferedImage, outputFolder);
            log.info("resizeImage resize success");
        } catch (Exception ex) {
            log.error("resizeImage resize 异常=" + ex.getMessage());
        }
    }

    //处理图片压缩变红
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    /**
     * 水印+图片
     * @param iconPath
     * @param srcImgPath
     * @param targerPath
     * @param degree
     * @author LaiZhengming
     */
    public static void markImageByIcon(String iconPath, String srcImgPath, String targerPath, Integer degree) {
        OutputStream os = null;
        try {
//            Image srcImg = ImageIO.read(new File(srcImgPath));
            Image srcImg=Toolkit.getDefaultToolkit().getImage(srcImgPath);
            BufferedImage bi=ImageUtil.toBufferedImage(srcImg);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();
            
            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            
            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 得到Image对象。
            Image img = imgIcon.getImage();
            float alpha = 0.5f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 表示水印图片的位置
            g.drawImage(img, 5, 10, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            os = new FileOutputStream(targerPath);
            // 生成图片
            ImageIO.write(buffImg, "JPG", os);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            try {
                if (null != os){
                	os.close();
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
    }
    
    /**
     * 保存web图片到本地目录下
     * @param picWebUrl
     * @param savePathDir
     * @return
     * @author wuling
     */
    public static String saveWebImg(String picWebUrl, String savePathDir, String realPath) {
        String saveFilePath = "";
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            URL url = new URL(picWebUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方式为"GET"
            urlConnection.setRequestMethod("GET");
            // 超时响应时间为5秒
            urlConnection.setConnectTimeout(5 * 1000);
            inputStream = urlConnection.getInputStream();
            // 创建图片保存目录
            File file = new File(realPath + savePathDir);
            if (!file.exists()) {
                FileUtils.forceMkdir(file);
            }
            // 文件保存路径
            String hashName = UUID.randomUUID().toString().replace("-", "");
            String extensionName = picWebUrl.substring(picWebUrl.lastIndexOf("."));
            saveFilePath = savePathDir + File.separator + hashName + extensionName;
            // 创建file文件-用于保存图片
            File picFile = new File(realPath + saveFilePath);
            // 保存文件
            byte[] picData = IOUtils.toByteArray(inputStream);
            inputStream.close();// 关闭输入流
            //
            outputStream = new FileOutputStream(picFile);
            outputStream.write(picData);
            outputStream.close();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            // 关闭流
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        return saveFilePath;
    }
    
    public static void main(String[] args) {
        log.info(getImgPath("D:/file", "aaa"));
       /* String srcImgPath = "e:/100.png";
        String iconPath = "e:/101.png";
        String targerPath = "e:/102.png";
        // 给图片添加水印
        ImageUtil.markImageByIcon(iconPath, srcImgPath, targerPath, -45);*/
    }
    
    public static void forceMkdir(File url){
    	try {
			FileUtils.forceMkdir(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
   /**
    * 创建文件存放路径
    * @param realPath 获取存放地址绝对路径
    * @param purpose 图片用途
    * @return
    * @since 2017年8月22日 下午5:49:18
    * @author yanyuanheng
    */
	public static String getImgPath(String realPath, String purpose) {
		String path = "";
		String savePath = "";
		// 读取配置文件中的图片保存的相对路径配置
		savePath = "uploadImg/bcw_server/" + purpose + "/"; // 文件保存路径
		String folderYMD = DateUtil.getCurrentDateStr("yyyyMMdd")+"/";
		try {
			File tempDir = new File(realPath + savePath + folderYMD);//
			FileUtils.forceMkdir(tempDir); // 新增目录[文件保存路径+日期目录]
			path = savePath + folderYMD;
		} catch (Exception ex) {
            log.error("新建文件夹失败 " + ex.getMessage());
        }
		return path;
    }
	
    /**
     * 获取文件名字
     * @return
     * @since 2017年8月22日 下午5:46:19
     * @author yanyuanheng
     */
	public static String getImgName() {
		String curPath = System.currentTimeMillis() + "" + ((int) Math.random() * 100000);
		return curPath;
    }
	
    /**
     * 上传图片 
     * @param imgfile 文件流
     * @param realPath 文件服务器路径
     * @param savePath 文件相对路径
     * @return
     * @throws IOException
     * @throws Exception
     * @since 2017年8月22日 下午6:06:15
     * @author yanyuanheng
     */
    public static String uploadImg(MultipartFile imgfile,
			String realPath, String savePath) throws IOException, Exception {
		String saveFile = "";
		InputStream is = imgfile.getInputStream();
		byte[] picData = IOUtils.toByteArray(is);
		if (picData != null && picData.length > 0) {
			String saveFileRelativeName = ImageUtil.saveImage(picData, savePath, realPath);
			saveFile = saveFileRelativeName;
		}
		return saveFile;
	}
}
