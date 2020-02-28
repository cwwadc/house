package com.msz.util;

import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author:Starry
 * @Description:
 * @Date:Created in 9:46 2018/4/13
 * Modified By:
 */
public class MD5Utils {

    static String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 将普通字符串用md5加密，并转化为16进制字符串
     *
     * @param str
     * @return
     */
    public static String MD5Encode(String str, String str1) {

        // 消息签名（摘要）
        MessageDigest md5 = null;
//        str = str + str1;
        try {
            // 参数代表的是算法名称
            md5 = MessageDigest.getInstance("md5");
            byte[] result = md5.digest(str1.getBytes());

            StringBuilder sb = new StringBuilder(32);
            // 将结果转为16进制字符  0~9 A~F
            for (int i = 0; i < result.length; i++) {
                // 一个字节对应两个字符
                byte x = result[i];
                // 取得高位
                int h = 0x0f & (x >>> 4);
                // 取得低位
                int l = 0x0f & x;
                sb.append(chars[h]).append(chars[l]);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String key, String text) throws Exception {
        //根据传入的密钥进行验证
        if (text.equalsIgnoreCase(key)) {
            return true;
        }
        return false;
    }
}