package com.msz.properties;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Project: admin
 * @Author: Jack
 * @CreateTime: 2018/3/30 17:22
 * @Describe:
 */
@Data
@ConfigurationProperties(prefix = "ftp")
public class FtpConfigProperties {

    private String host = "localhost";

    private int port = FTPClient.DEFAULT_PORT;

    private String username;

    private String password;

    private int bufferSize = 1024 * 8;

    /**
     * 初始化连接数
     */
    private Integer initialSize = 0;

    /**
     * 字符编码集
     */
    private String encoding = "utf-8";
}
