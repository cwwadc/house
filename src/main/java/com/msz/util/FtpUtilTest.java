package com.msz.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;

public class FtpUtilTest {
    private static Logger logger = Logger.getLogger(FtpUtil.class);

    /**
     * 登陆FTP并获取FTPClient对象
     *
     * @param host     FTP主机地址
     * @param port     FTP端口
     * @param userName 登录用户名
     * @param password 登录密码
     * @return
     */
    public static FTPClient loginFTP(String host, int port, String userName, String password) {
        FTPClient ftpClient = null;
        try {

            ftpClient = new FTPClient();
            // 连接FTP服务器
            ftpClient.connect(host, port);
            // 登陆FTP服务器
            ftpClient.login(userName, password);
            // 中文支持
            ftpClient.setControlEncoding("UTF-8");
            // 设置文件类型为二进制（如果从FTP下载或上传的文件是压缩文件的时候，不进行该设置可能会导致获取的压缩文件解压失败）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.error("连接FTP失败，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功!");
            }
        } catch (Exception e) {
            logger.error("登陆FTP失败，请检查FTP相关配置信息是否正确！", e);
        }
        return ftpClient;
    }

    /**
     * 从FTP下载文件到本地
     * @param ftpClient     已经登陆成功的FTPClient
     * @param ftpFilePath   FTP上的目标文件路径
     * @param localFilePath 下载到本地的文件路径
     */
    public static void downloadFileFromFTP(FTPClient ftpClient, String ftpFilePath, String localFilePath) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 获取ftp上的文件
            is = ftpClient.retrieveFileStream(ftpFilePath);
            fos = new FileOutputStream(new File(localFilePath));
            // 文件读取方式一
            int i;
            byte[] bytes = new byte[1024];
            while ((i = is.read(bytes)) != -1) {
                fos.write(bytes, 0, i);
            }
            // 文件读取方式二
            //ftpClient.retrieveFile(ftpFilePath, new FileOutputStream(new File(localFilePath)));
            ftpClient.completePendingCommand();
            logger.info("FTP文件下载成功！");
        } catch (Exception e) {
            logger.error("FTP文件下载失败！", e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传本地文件到FTP
     * @param ftpClient         已经登陆成功的FTPClient
     * @param localFilePath     要上传的本地文件路径
     * @param ftpFilePath       要保存的FTP文件路径
     */
    public static void uploadFileToFTP(FTPClient ftpClient, String localFilePath, String ftpFilePath) {
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            // 获取ftp上的文件
            os = ftpClient.storeFileStream(ftpFilePath);
            fis = new FileInputStream(new File(localFilePath));
//            ftpClient.enterLocalActiveMode(); //外网ip上传主动模式
            // 文件保存方式一
//            int length;
//            byte[] bytes = new byte[1024];
//            while ((length = fis.read(bytes)) != -1) {
//                os.write(bytes, 0, length);
//            }
            // 文件保存方式二
            //设置为被动模式
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile(ftpFilePath, new FileInputStream(new File(localFilePath)));
            ftpClient.completePendingCommand();
            logger.info("FTP文件上传成功！");
        } catch (Exception e) {
            logger.error("FTP文件上传失败！", e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取FTP某一特定目录下的所有文件名称
     * @param ftpClient     已经登陆成功的FTPClient
     * @param ftpDirPath    FTP上的目标文件路径
     */
    public void getFileNameListFromFTP(FTPClient ftpClient, String ftpDirPath) {
        try {
            if (ftpDirPath.startsWith("/") && ftpDirPath.endsWith("/")) {
                // 通过提供的文件路径获取FTPFile对象列表
                FTPFile[] files = ftpClient.listFiles(ftpDirPath);
                // 遍历文件列表，打印出文件名称
                for (int i = 0; i < files.length; i++) {
                    FTPFile ftpFile = files[i];
                    // 此处只打印文件，未遍历子目录（如果需要遍历，加上递归逻辑即可）
                    if (ftpFile.isFile()) {
                        logger.info(ftpDirPath + ftpFile.getName());
                    }
                }
            } else {
                logger.error("当前FTP路径不可用");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
