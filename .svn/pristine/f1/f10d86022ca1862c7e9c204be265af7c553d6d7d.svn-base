package com.msz.util.config;

import com.msz.properties.FtpConfigProperties;
import com.msz.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @Project: admin
 * @Author: Jack
 * @CreateTime: 2018/3/6 12:45
 * @Describe: FTP配置类
 */
@Slf4j
@Configuration
@ConditionalOnClass({GenericObjectPool.class, FTPClient.class})
@ConditionalOnProperty(value = "ftp.enabled", havingValue = "true")
@EnableConfigurationProperties(FtpConfigProperties.class)
public class FtpConfig {

    private ObjectPool<FTPClient> pool;

    public FtpConfig(FtpConfigProperties aa) {
        // 默认最大连接数与最大空闲连接数都为8，最小空闲连接数为0
        // 其他未设置属性使用默认值，可根据需要添加相关配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxTotal(-1);
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setMinEvictableIdleTimeMillis(3000);
        poolConfig.setSoftMinEvictableIdleTimeMillis(3000);
        poolConfig.setTimeBetweenEvictionRunsMillis(3000);
        poolConfig.setNumTestsPerEvictionRun(3);
        // 连接池耗尽后是否一直阻塞
        poolConfig.setBlockWhenExhausted(false);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestWhileIdle(false);
        poolConfig.setLifo(false);
        poolConfig.setFairness(true);
        poolConfig.setEvictorShutdownTimeoutMillis(1000);
        pool = new GenericObjectPool<>(new FtpClientPooledObjectFactory(aa), poolConfig);
        preLoadingFtpClient(aa.getInitialSize(), poolConfig.getMaxIdle());
        // 初始化ftp工具类中的ftpClientPool
        FtpUtil.init(pool);

    }

    /**
     * 预先加载FTPClient连接到对象池中
     *
     * @param initialSize 初始化连接数
     * @param maxIdle     最大空闲连接数
     */
    private void preLoadingFtpClient(Integer initialSize, int maxIdle) {
        if (initialSize == null || initialSize <= 0) {
            return;
        }
        int size = Math.min(initialSize.intValue(), maxIdle);
        for (int i = 0; i < size; i++) {
            try {
                pool.addObject();
            } catch (Exception e) {
                log.error("preLoadingFtpClient error...", e);
            }
        }
    }

    @PreDestroy
    public void destroy() {
        if (pool != null) {
            pool.close();
            log.info("销毁ftpClientPool...");
        }
    }


    /**
     * FtpClient对象工厂类
     */
    static class FtpClientPooledObjectFactory implements PooledObjectFactory<FTPClient> {

        private FtpConfigProperties props;

        public FtpClientPooledObjectFactory(FtpConfigProperties props) {
            this.props = props;
        }

        @Override
        public PooledObject<FTPClient> makeObject() throws Exception {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.setDefaultPort(props.getPort());
                ftpClient.connect(props.getHost());
                ftpClient.login(props.getUsername(), props.getPassword());

                log.info("连接FTP服务器返回码{}", ftpClient.getReplyCode());
                ftpClient.setBufferSize(props.getBufferSize());
                ftpClient.setControlEncoding(props.getEncoding());
                ftpClient.setConnectTimeout(3000);
                ftpClient.setDataTimeout(3000);
                ftpClient.setDefaultTimeout(3000);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

                ftpClient.enterLocalActiveMode();
                // 根据自身配置选择模式
                log.info("ftp得连接模式为:{}", ftpClient.getDataConnectionMode());

                return new DefaultPooledObject<>(ftpClient);
            } catch (Exception e) {
                log.error("建立FTP连接失败", e);
                if (ftpClient.isAvailable()) {
                    ftpClient.disconnect();
                }
                ftpClient = null;
                throw new Exception("建立FTP连接失败", e);
            }
        }


        @Override
        public void destroyObject(PooledObject<FTPClient> p) throws Exception {
            FTPClient ftpClient = getObject(p);
            try {
            	if(ftpClient != null && ftpClient.isConnected()) {
            		ftpClient.logout();
            	}
            } catch (IOException ex) {
            	log.error("注销ftpClient失败");
            } finally {
                // 注意,一定要在finally代码中断开连接，否则会导致占用ftp连接情况
                try {
                     ftpClient.disconnect();
                    log.info("断开ftpClient成功");
                } catch (IOException io) {
                	log.error("断开ftpClient失败");
                }
            }
        }

        @Override
        public boolean validateObject(PooledObject<FTPClient> p) {
            FTPClient ftpClient = getObject(p);
            if (ftpClient == null || !ftpClient.isConnected()) {
                return false;
            }
            try {
                ftpClient.changeWorkingDirectory("/");
                return true;
            } catch (Exception e) {
                log.error("验证FTP连接失败::{}", ExceptionUtils.getStackTrace(e));
                return false;
            }
        }

        @Override
        public void activateObject(PooledObject<FTPClient> p) throws Exception {
        }

        @Override
        public void passivateObject(PooledObject<FTPClient> p) throws Exception {
        }

        private FTPClient getObject(PooledObject<FTPClient> p) {
            if (p == null || p.getObject() == null) {
                return null;
            }
            return p.getObject();
        }


    }

}
