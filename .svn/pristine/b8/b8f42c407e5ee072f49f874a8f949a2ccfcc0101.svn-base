package com.msz.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片缩放
 * @author Administrator
 */
@Slf4j
public class ResizeImage {
    
    /**
     * @param im 原始图像
     * @param resizeTimes 需要缩小的倍数，缩小2倍为原来的1/2 ，这个数值越大，返回的图片越小
     * @return 返回处理后的图像
     */
    public BufferedImage resizeImage(BufferedImage im, float resizeTimes) {
        /* 原始图像的宽度和高度 */
        int width = im.getWidth();
        int height = im.getHeight();
        /* 调整后的图片的宽度和高度 */
        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / resizeTimes);
        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / resizeTimes);
        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * @param im 原始图像
     * @param resizeTimes 倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return 返回处理后的图像
     */

    public BufferedImage zoomImage(BufferedImage im, float resizeTimes) {
        /* 原始图像的宽度和高度 */
        int width = im.getWidth();
        int height = im.getHeight();
        /* 调整后的图片的宽度和高度 */
        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) * resizeTimes);
        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) * resizeTimes);
        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * 填充图片至正方形，宽高以最大值为准
     * @param targetFile
     * @return
     */
    public BufferedImage fillImage(File targetFile) throws Exception {
        Image srcImg=Toolkit.getDefaultToolkit().getImage(targetFile.getPath());
        BufferedImage im=ImageUtil.toBufferedImage(srcImg);
//        BufferedImage im = javax.imageio.ImageIO.read(targetFile);
        int width = im.getWidth();
        int height = im.getHeight();
        int fillValue = 0;
        if (width >= height) {
            fillValue = width;
        } else {
            fillValue = height;
        }
        if (width == height) {
            return im;
        }

        //  将图片填充至正方形 一、取颜色；二、填充；三、缩放
        int[] rgb = new int[3];
        int pixel = im.getRGB(1, 1);
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);

        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(fillValue, fillValue, BufferedImage.TYPE_INT_RGB);
        // 填充背景色
        Graphics graphics = result.getGraphics();
        graphics.setColor(new Color(rgb[0], rgb[1], rgb[2], 255));
        // 根据宽高计算 填充颜色和位置
        graphics.fillRect(0, 0, fillValue, fillValue);
        /*
         * if (height>width)//高大于宽 填宽 { int temp = (height-width)/2;//宽差值的1/2 graphics.fillRect(0-temp, 0, temp, height); //填左边 graphics.fillRect(width,0,temp,height); //填右边 } else //填高 { //graphics.fillRect(0,0,fillValue,fillValue); int temp = (width-height)/2;//高差值的1/2 graphics.fillRect(0,
         * 0-height-temp, width, temp); //填下面 graphics.fillRect(width,0+temp,width,temp); //填上面 }
         */
        graphics.drawImage(im.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        return result;
    }

    /**
     * 指定高度
     * @param im
     * @param toWidth
     * @param toHeight
     * @return
     */
    public BufferedImage zoomImage(BufferedImage im, int toWidth, int toHeight) {
        //  原则：1、如果宽高都大于150，则自动缩放处理；2、都小于150，则按宽比例放大
        int width = im.getWidth();
        int height = im.getHeight();
        // float bit = 1f;
        // 计算尺寸比例 2012-07-24
        double sx = (double) toWidth / 1; // 150/200
        double sy = (double) toHeight / 1;// 150/220
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx >= 1 && sy >= 1) {
            // 如果源图高于小缩放高
            return im;
        }
        if (sx > sy) { // 宽缩放比例大于高比例 偏瘦
            sx = sy;
            toWidth = (int) (sx * width);
        } else { // 偏胖
            sy = sx;
            toHeight = (int) (sy * height);
        }

        /*
         * if ((width<toWidth)&&(height<toHeight))//如果源图高于小缩放高 { toWidth = width; toHeight = height; return im;//直接不进行任何处理，返回源图 }else if (width<toWidth) //如果源图宽于小缩放宽，将按比例缩放 { bit = (float)width/(float)toWidth; // 120 150 toWidth = width;//(int) (width * bit); if (height>toHeight) toHeight = (int)
         * (height * bit); else toHeight = height; }else if (height<toHeight) { bit = (float)height/(float)toHeight; if (width>toWidth) toWidth = (int) (width * bit); else toWidth = width; toHeight = height;//(int) (height * bit); }
         */
        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        // 填充背景色
        Graphics graphics = result.getGraphics();
        // graphics.setColor(new Color(0,0,0,255));
        // graphics.fillRect(0, 0, toWidth, toHeight);
        graphics.drawImage(im.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }
    
    /**
     * @param path 要转化的图像的文件夹,就是存放图像的文件夹路径
     * @param type 图片的后缀名组成的数组
     * @return
     */
    public List<BufferedImage> getImageList(String path, String[] type) throws IOException {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (String s : type) {
            map.put(s, true);
        }
        List<BufferedImage> result = new ArrayList<BufferedImage>();
        File[] fileList = new File(path).listFiles();
        for (File f : fileList) {
            if (f.length() == 0) {
                continue;
            }
            if (map.get(getExtension(f.getName())) == null) {
                continue;
            }
            Image srcImg=Toolkit.getDefaultToolkit().getImage(f.getPath());
            BufferedImage im=ImageUtil.toBufferedImage(srcImg);
            result.add(im);
            //result.add(javax.imageio.ImageIO.read(f));
        }
        return result;
    }
    
    /**
     * @param path 要转化的图像的文件夹,就是存放图像的文件夹路径
     * @return
     */
    public List<String> getFileListBySubFolder(String path) throws IOException {
        List<String> files = new ArrayList<String>();
        File[] fileList = new File(path).listFiles();
        for (File f : fileList) {
            if (f.isDirectory()) {
                continue;
            }
            files.add(f.getName());
        }
        return files;
    }
    
    /**
     * 把图片写到磁盘上
     * @param im
     * @param path eg: C://home// 图片写入的文件夹地址
     * @param fileName DCM1987.jpg 写入图片的名字
     * @return
     */
    public boolean writeToDisk(BufferedImage im, String path, String fileName) {
        File f = new File(path + fileName);
        String fileType = getExtension(fileName);
        if (fileType == null) {
            return false;
        }
        try {
            ImageIO.write(im, fileType, f);
            im.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public boolean writeHighQuality(String newFilename, BufferedImage im, String fileFullPath) {
        try {
            String dstName = fileFullPath + File.separator + newFilename;
            String formatName = dstName.substring(dstName.lastIndexOf(".") + 1);// 类型名称，如jpg；用于BufferImg=>ImageWrite
            ImageIO.write(im, formatName, new File(dstName));
            return true;
        } catch (Exception e) {
            log.info("writeHighQuality异常=" + e);
            return false;
        }
    }
    
    /**
     * 返回文件的文件后缀名
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 查询子文件夹
     * @return
     */
    public List<String> getSubFolders(String path, String type) {
        List<String> folders = new ArrayList<String>();
        File[] fileList = new File(path).listFiles();
        for (File f : fileList) {
            if (!f.isDirectory()) {
                continue;
            }
            if (!f.getName().startsWith(type)) {
                continue;
            }
            folders.add(f.getName());
        }
        return folders;
    }
    
    /**
     * 返回文件的文件后名，不带后缀
     * @param fileName
     * @return
     */
    public String getFilenameWithoutExtension(String fileName) {
        try {
            String ext = getExtension(fileName);
            String filename = fileName.substring(0, fileName.indexOf(ext) - 1);
            return filename;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 处理图片
     * @return
     */
    public void resizeImages(Integer width, Integer height, String type) throws Exception {
        // 源图存放处
        String inputFoler = "/Users/xueyanbeijing/Documents/workitem/真假通应用/客户端/测试版发布/productpicture/img_big/";
        /* 从img_big复制过来的图片地址 */// 需要缩放的程序存放处
        String tempFolder = "/Users/xueyanbeijing/Documents/workitem/真假通应用/客户端/测试版发布/productpicture/temp_img_big_resize/";
        /* 这儿填写你存放要缩小图片的文件夹全地址 */
        String outputFolder = "/Users/xueyanbeijing/Documents/workitem/真假通应用/客户端/测试版发布/productpicture/img_big_resize/";
        // String outputFolder = "D:\\ssports\\Documents\\0.开发\\4.pic\\static\\";
        /* 这儿填写你转化后的图片存放的文件夹 */
        /* 这个参数是要转化成的倍数,如果是1就是转化成1倍 */
        
        // 逻辑：一、从img_big复制图片至 temp_img_big_resize 二、将 temp_img_big_resize 缩放放至 img_big_resize
        String filename = "0A7A7943E96327E53C0AE9701AB277AD.jpg";
        File sourceFile = new File(inputFoler + filename);
        String middleFolder = filename.substring(0, 2);// 以前两个字母为文件夹
        File tempFileFolder = new File(tempFolder + middleFolder);
        if (!tempFileFolder.exists()) {
            tempFileFolder.mkdir();
        }
        File tempTargetFile = new File(tempFolder + middleFolder + "/" + filename);
        copyFile(sourceFile, tempTargetFile); // 复制文件
        // 缩放
        File targetResizeFolder = new File(outputFolder + middleFolder);
        if (!targetResizeFolder.exists()) {
            targetResizeFolder.mkdir();
        }
        outputFolder = outputFolder + middleFolder + "/";
        // ResizeImage r = new ResizeImage();
        Image srcImg=Toolkit.getDefaultToolkit().getImage(tempTargetFile.getPath());
        BufferedImage bi=ImageUtil.toBufferedImage(srcImg);
//        BufferedImage bi = javax.imageio.ImageIO.read(tempTargetFile);
        writeHighQuality(filename, zoomImage(bi, width, height), outputFolder);
        
        // writeToDisk(bi, outputFolder, filename);
        
        /*
         * List<String> folders = this.getSubFolders(inputFoler, type); for (String folder:folders) { ResizeImage r = new ResizeImage(); String inputSubFoler = inputFoler +folder; String outputSubFoler = outputFolder +folder; File temp = new
         * File(outputSubFoler); if (!temp.isFile()) temp.mkdir(); outputSubFoler = outputSubFoler +"\\"; List<String> files = getFileListBySubFolder(inputSubFoler); List<BufferedImage> imageList = r.getImageList(inputSubFoler,new String[] {"jpg","JPG","jpeg","JPEG","PNG","png"});
         * //{"jpg","JPG","jpeg","JPEG","PNG","png"}); for(int i =0;i<imageList.size();i++) { BufferedImage bi = imageList.get(i); String newFilename = this.getFilenameWithoutExtension(files.get(i))+"_"+width+"_"+height+"."+this.getExtension(files.get(i));//新文件名
         *  r.writeHighQuality(newFilename,r.zoomImage(bi,width,height),outputSubFoler); writeToDisk(bi, outputSubFoler, this.getFilenameWithoutExtension(files.get(i))+"."+this.getExtension(files.get(i))); } }
         */
        
    }
    
    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            
            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null) {
                inBuff.close();
            }
            if (outBuff != null) {
                outBuff.close();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        // ResizeImage ri = new ResizeImage();
        // ri.resizeImages(150,150,"");
        // ri.resizeImages(145,200,"p_");//145x200（球员档案页面用）
        // // ri.resizeImages(227,198,"c_");//227x198（教练档案页面用）
        // ri.resizeImages(678,461,"f_");//678x461（球场档案页面用）
        
        // ri.resizeImages(42,42,"t_");//42x42（球队球员页面用）
        // ri.resizeImages(27,36,"t_");//27x36（球员信息页面用）
        // ri.resizeImages(50,50,"t_");//50x50（比赛前瞻，回顾页面用）
        // ri.resizeImages(36,36,"t_");//36x36（比赛前瞻，回顾页面用）
        // ri.resizeImages(20,20,"t_");//20x20（比赛前瞻，回顾页面用）
        // ri.resizeImages(227,198,"t_");//227x198（球队档案页面用）
        // ri.resizeImages(43,40,"t_");//43x40（视觉英超页面用）
        
        // default//
        // ri.resizeImages(630,349,"default_");//145x200（球员档案页面用）
        // ri.resizeImages(184,108,"default_");//227x198（教练档案页面用）
        // ri.resizeImages(130,100,"default_");//678x461（球场档案页面用）
        //
        // ri.resizeImages(120,80,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(454,394,"default_");//27x36（球员信息页面用）
        // ri.resizeImages(623,340,"default_");//50x50（比赛前瞻，回顾页面用）
        // ri.resizeImages(460,348,"default_");//36x36（比赛前瞻，回顾页面用）
        // ri.resizeImages(155,110,"default_");//20x20（比赛前瞻，回顾页面用）
        // ri.resizeImages(233,233,"default_");//227x198（球队档案页面用）
        // ri.resizeImages(255,245,"default_");//43x40（视觉英超页面用）
        // ri.resizeImages(640,466,"default_");//43x40（视觉英超页面用）
        // ri.resizeImages(120,90,"default_");//43x40（视觉英超页面用）
        
        // 2012-8-8 尺寸更改后图片缩略图
        // ri.resizeImages(468,351,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(132,99,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(560,420,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(120,90,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(460,345,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(468,351,"default_");//42x42（球队球员页面用）
        // ri.resizeImages(148,111,"default_");//42x42（球队球员页面用）
        
        // 630x349（视觉英超页面用）
        // 184x108（视觉英超页面用）
        // 130x100（视觉英超页面用）
        // 120x80（英超新闻页面用）
        // 454x394（新闻终极页面用）
        // 623x340（比赛回顾页面使用）
        
        // 460x348（首页今日主题）
        // 155x110（首页今日主题）
        // 233x233（首页今日主题封面）
        // 255x245（首页跑马灯）
        // 宽度不定，高度18（首页转播中国LOGO）
        // 640x466（视觉英超弹层大图）
        // 120x90（视觉英超弹层小图）
        
    }
    
}
