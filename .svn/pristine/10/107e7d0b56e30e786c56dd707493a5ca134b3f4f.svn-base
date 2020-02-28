package com.msz.controller;


import com.msz.common.RespEntity;
import com.msz.util.SFTPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenwanwan
 * @ClassName UploadController
 * @Description 图片提交的controller类(这个上传图片是需要上传验证登陆)
 * @since 2016年12月28日 下午9:09:44
 */

@Slf4j
@Api(value = "/uploadController", description = " 图片上传接口")
@RestController
@RequestMapping("/upload")
public class UploadController {

    private String UPLOAD_PATH = "/image";

    @Value("${cylinder-sftp-name}")
    private String username;
    @Value("${cylinder-sftp-password}")
    private String password;
    @Value("${cylinder-sftp-host}")
    private String host;
    @Value("${cylinder-sftp-port}")
    private int port;
    @Value("${http-image-path}")
    private String httpImagePath;

    @ApiOperation(value = "sftp上传文件", notes = "sftp上传文件")
    @PostMapping(value = "/sftpUpload")
    public RespEntity ftpUpload(@RequestParam("sftpUpload") MultipartFile file) {
        if(file == null ){
            return RespEntity.badRequest("imageFile不能为空");
        }
        if (file.getSize() >= 10*1024*1024)
        {
            return RespEntity.badRequest("文件不能大于10M");
        }
        SFTPUtil sftpUtil = new SFTPUtil(username, password, host,port);
        sftpUtil.login();
        //	获取文件原名称
        String fileName = file.getOriginalFilename();
        //  文件尾缀并转小写
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
        //	定义存储数据库图片名称
        String realName = "";
        //	功能文件夹,注意：代码只能创建一级文件夹，需先在服务器创建文件夹目录
//        String tmpPath = "/uploadImg";
        String directory = "";
        //	时间文件夹
        String folderYMD = new DateTime().toString("yyyyMMdd") + "/";
        boolean flag = false;
        try {
            InputStream is = file.getInputStream();
            byte[] picData = IOUtils.toByteArray(is);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(picData);
            if (picData != null && picData.length > 0) try {
                //	真实文件名,DigestUtils.md5Hex方法可以根据文件byte[]生成唯一哈希!!!(节省服务器内存)
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                    String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                Long time = System.currentTimeMillis();
                realName = time + DigestUtils.md5Hex(picData) + suffix;
                //	文件在图片服务器的路径前缀
                directory = "/" + folderYMD;
                //  ftp上传文件
                sftpUtil.upload(UPLOAD_PATH, directory, realName, byteArrayInputStream);
                sftpUtil.logout();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(realName) && flag) {
            String absoluteName =  directory +realName;
            //  获取图片尾缀，拼接图片服务器前缀
            String imageUrl= httpImagePath + absoluteName;
            return RespEntity.ok().setResponseContent(imageUrl);
        } else {
            return RespEntity.badRequest("{\"errno\":1,\"data\":[]}");
        }
    }
}
