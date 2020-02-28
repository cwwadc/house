package com.msz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.ObjectPool;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Project: admin
 * @Author: Jack
 * @CreateTime: 2018/3/6 12:47
 * @Describe: FTP工具类
 */
@Slf4j
@Component
public class FtpUtil {

    /**
     * ftpClient连接池初始化标志
     */
    private static volatile boolean hasInit = false;
    /**
     * ftpClient连接池
     */
    private static ObjectPool<FTPClient> ftpClientPool;

    /**
     * 初始化ftpClientPool
     *
     * @param ftpClientPool
     */
    public static void init(ObjectPool<FTPClient> ftpClientPool) {
        if (!hasInit) {
            synchronized (FtpUtil.class) {
                if (!hasInit) {
                    FtpUtil.ftpClientPool = ftpClientPool;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 完成后续指令
     *
     * @return
     */
    public static boolean completePendingCommand() {
        try {
            log.info("回复状态:{}", getFtpClient().getReply());
            return FTPReply.isPositiveCompletion(getFtpClient().getReply());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取csv文件
     *
     * @param remoteFilePath 文件路径（path+fileName）
     * @param headers        列头
     * @return
     * @throws IOException
     */
    public static List<CSVRecord> readCsvFile(String remoteFilePath, String... headers) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath))) {
            return CSVFormat.EXCEL.withHeader(headers).withSkipHeaderRecord(false)
                    .withIgnoreSurroundingSpaces().withIgnoreEmptyLines()
                    .parse(new InputStreamReader(in, "utf-8")).getRecords();
        } finally {
            completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 按行读取FTP文件
     *
     * @param remoteFilePath 文件路径（path+fileName）
     * @return
     * @throws IOException
     */
    public static List<String> readFileByLine(String remoteFilePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath));
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            return br.lines().map(line -> StringUtils.trimToEmpty(line))
                    .filter(line -> StringUtils.isNotEmpty(line)).collect(Collectors.toList());
        } finally {
            completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件
     *
     * @param remotePath 路径
     * @return FTPFile数组
     * @throws IOException
     */
    public static FTPFile[] retrieveFTPFiles(String remotePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try {
            return ftpClient.listFiles(encodingPath(remotePath + "/"),
                    file -> file != null && file.getSize() > 0);
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件名称
     *
     * @param remotePath 路径
     * @return ftp文件名称列表
     * @throws IOException
     */
    public static List<String> retrieveFileNames(String remotePath) throws IOException {
        FTPFile[] ftpFiles = retrieveFTPFiles(remotePath);
        if (ArrayUtils.isEmpty(ftpFiles)) {
            return new ArrayList<>();
        }
        return Arrays.stream(ftpFiles).filter(Objects::nonNull)
                .map(FTPFile::getName).collect(Collectors.toList());
    }

    /**
     * 编码文件路径
     */
    private static String encodingPath(String path) throws UnsupportedEncodingException {
        // FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
        return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
    }

    /**
     * 获取ftpClient
     *
     * @return
     */
    private static FTPClient getFtpClient() {
        checkFtpClientPoolAvailable();
        FTPClient ftpClient = null;
        Exception ex = null;
        // 获取连接最多尝试3次
        for (int i = 0; i < 3; i++) {
            try {
                ftpClient = ftpClientPool.borrowObject();
                ftpClient.changeWorkingDirectory("/");
                break;
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ftpClient == null) {
            throw new RuntimeException("Could not get a ftpClient from the pool", ex);
        }
        return ftpClient;
    }


    /**
     * 释放ftpClient
     */
    private static void releaseFtpClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }
        try {
            ftpClientPool.returnObject(ftpClient);
        } catch (Exception e) {
            log.error("Could not return the ftpClient to the pool", e);
            if (ftpClient.isAvailable()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException io) {
                }
            }
        } finally {
            log.info("ftp状态:active==>{},idle==>{}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
            try {
                ftpClientPool.clear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 检查ftpClientPool是否可用
     */
    private static void checkFtpClientPoolAvailable() {
        Assert.state(hasInit, "FTP未启用或连接失败！");
    }

    /**
     * ftp 创建文件夹
     *
     * @param ftpPath
     * @return
     */
    public static boolean mkDir(String ftpPath) {
        FTPClient ftpClient = getFtpClient();
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // 将路径中的斜杠统一
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {

                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            if (ftpPath.indexOf('/') == -1) {
                // 只有一层目录
                ftpClient.makeDirectory(new String(ftpPath.getBytes(),
                        "iso-8859-1"));
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),
                        "iso-8859-1"));
            } else {
                // 多层目录循环创建
                String[] paths = ftpPath.split("/");
                String pathTemp = "";
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.makeDirectory(new String(paths[i].getBytes(),
                            "iso-8859-1"));
                    ftpClient.changeWorkingDirectory(new String(paths[i]
                            .getBytes(), "iso-8859-1"));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传Excel文件到FTP
     *
     * @param workbook
     * @param remoteFilePath
     * @throws IOException
     */
//    public static boolean uploadExcel2Ftp(Workbook workbook, String remoteFilePath)
//            throws IOException {
//        Assert.notNull(workbook, "workbook cannot be null.");
//        Assert.hasText(remoteFilePath, "remoteFilePath cannot be null or blank.");
//        FTPClient ftpClient = getFtpClient();
//        try (OutputStream out = ftpClient.storeFileStream(encodingPath(remoteFilePath))) {
//            workbook.write(out);
//            workbook.close();
//            return true;
//        } finally {
//            ftpClient.completePendingCommand();
//            releaseFtpClient(ftpClient);
//        }
//    }

    /**
     * 从ftp下载excel文件
     *
     * @param remoteFilePath
     * @param response
     * @throws IOException
     */
    public static void downloadExcel(String remoteFilePath, HttpServletResponse response)
            throws IOException {
        String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
        fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath));
             OutputStream out = response.getOutputStream()) {
            int size = 0;
            byte[] buf = new byte[10240];
            while ((size = in.read(buf)) > 0) {
                out.write(buf, 0, size);
                out.flush();
            }
        } finally {
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * ftp上传文件(不修改原文件名)
     *
     * @param localDirectoryAndFileName
     * @param ftpDirectory
     * @return
     */
    public static boolean uploadLocalFile(String localDirectoryAndFileName, String ftpDirectory) {
        String fileName = localDirectoryAndFileName.substring(localDirectoryAndFileName.lastIndexOf("/") + 1);
        return uploadLocalFile(localDirectoryAndFileName, fileName, ftpDirectory);
    }

    /**
     * ftp上传文件
     *
     * @param localDirectoryAndFileName
     * @param ftpFileName
     * @param ftpDirectory
     * @return
     */
    public static boolean uploadLocalFile(String localDirectoryAndFileName, String ftpFileName,
                                          String ftpDirectory) {
        Boolean flag = false;
        FTPClient ftpClient = getFtpClient();
        File srcFile = new File(localDirectoryAndFileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(srcFile);
            mkDir(ftpDirectory);
            ftpClient.changeWorkingDirectory(ftpDirectory);
            String remoteFileName = new String(ftpFileName.getBytes("GBK"), "iso-8859-1");
            ftpClient.enterLocalPassiveMode();
            flag = ftpClient.storeFile(remoteFileName, fis);
            if (flag) {
                log.info("本地文件:{}已经上传成功!ftp路径为:{}-{}", localDirectoryAndFileName, ftpDirectory, ftpFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ftp上传文件出错!", e);
        } finally {
            IOUtils.closeQuietly(fis);
            releaseFtpClient(ftpClient);
        }
        return flag;
    }

    /**
     * 上传流文件
     *
     * @param inputStream
     * @param ftpDirectory
     * @return
     */
    public boolean uploadInputStreamFile(InputStream inputStream, String realName, String ftpDirectory) {
        FTPClient ftpClient = getFtpClient();
        boolean uploadFlag = false;
        try {
            String remote = "";
            if (ftpDirectory.endsWith("/")) {
                remote = ftpDirectory + realName;
            } else {
                remote = ftpDirectory + "/" + realName;
            }
            mkDir(ftpDirectory);
            ftpClient.changeWorkingDirectory(ftpDirectory);
            remote = new String(remote.getBytes("GBK"), "iso-8859-1");
            ftpClient.setRemoteVerificationEnabled(true);
//            ftpClient.enterLocalPassiveMode();
            uploadFlag = ftpClient.storeFile(remote, inputStream);
            log.info("上传图片_{}==>{}", remote, uploadFlag == true ? "成功!" : "失败!");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.closeQuietly(inputStream);
            log.info("关闭{}的文件流", realName);
            releaseFtpClient(ftpClient);
        }
        return uploadFlag;
    }
//    public boolean uploadInputStreamFile(InputStream inputStream, String realName, String ftpDirectory) {
//        FTPClient ftpClient = getFtpClient();
//        try {
//            String remote = "";
//            if (ftpDirectory.endsWith("/")) {
//                remote = ftpDirectory + realName;
//            } else {
//                remote = ftpDirectory + "/" + realName;
//            }
//            mkDir(ftpDirectory);
//            ftpClient.changeWorkingDirectory(ftpDirectory);
//            remote = new String(remote.getBytes("GBK"), "iso-8859-1");
////            ftpClient.enterLocalPassiveMode();
//            ftpClient.storeFile(remote, inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    /**
     * ftp远程文件下载
     *
     * @param ftpDirectoryAndFileName
     * @param localDirectoryAndFileName
     */
    public static void downloadRemoteFile(String ftpDirectoryAndFileName, String localDirectoryAndFileName) {
        String filePath = ftpDirectoryAndFileName.substring(0, ftpDirectoryAndFileName.lastIndexOf("/"));
        String fileName = ftpDirectoryAndFileName.substring(ftpDirectoryAndFileName.lastIndexOf("/") + 1);
        try {
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FTPClient ftpClient = getFtpClient();
        InputStream inputStream = null;
        try {
            ftpClient.changeWorkingDirectory(filePath);
            inputStream = ftpClient.retrieveFileStream(fileName);
            FileCopyUtils.copy(inputStream, new FileOutputStream(localDirectoryAndFileName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * FTP删除远程文件
     *
     * @param ftpDirectoryAndFileName
     * @return
     */
    public static boolean deleteRemoteFile(String ftpDirectoryAndFileName) {
        FTPClient ftpClient = getFtpClient();
        Boolean flag = false;
        try {
            flag = ftpClient.deleteFile(ftpDirectoryAndFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            releaseFtpClient(ftpClient);
        }
        return flag;
    }

    /**
     * ftp删除远程文件
     *
     * @param ftpDirectory
     * @param fileName
     * @return
     */
    public static boolean deleteRemoteFile(String ftpDirectory, String fileName) {
        return deleteRemoteFile(ftpDirectory + fileName);
    }

    /**
     * ftp移除远程文件夹
     *
     * @param ftpDirectory
     * @return
     */
    public static boolean removeDirectory(String ftpDirectory) {
        FTPClient ftpClient = getFtpClient();
        Boolean flag = false;
        try {
            flag = ftpClient.removeDirectory(ftpDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            releaseFtpClient(ftpClient);
        }
        return flag;
    }

    /**
     * 移除文件夹下所有文件或移除单个文件
     *
     * @param pathName
     * @return
     */
    public static boolean removeAll(String pathName) {
        FTPClient ftpClient = getFtpClient();
        try {
            FTPFile[] files = ftpClient.listFiles(pathName);
            if (null != files && files.length > 0) {
                for (FTPFile file : files) {
                    if (file.isDirectory()) {
                        removeAll(pathName + "/" + file.getName());
                        // 切换到父目录，不然删不掉文件夹
                        ftpClient.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));
                        ftpClient.removeDirectory(pathName);
                    } else {
                        if (!ftpClient.deleteFile(pathName + "/" + file.getName())) {
                            ftpClient.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));
                            ftpClient.deleteFile(pathName);
                        }
                    }
                }
            }
            // 切换到父目录，不然删不掉文件夹
            ftpClient.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));
            ftpClient.removeDirectory(pathName);
        } catch (IOException e) {
            log.error("删除指定文件夹" + pathName + "失败：" + e);
            e.printStackTrace();
            return false;
        } finally {
            releaseFtpClient(ftpClient);
        }
        return true;
    }
}
