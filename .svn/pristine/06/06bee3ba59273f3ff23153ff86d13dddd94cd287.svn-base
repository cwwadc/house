package com.msz.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author Maoyy
 * @Description 在此声明加密规则: 账号 + 密码 + createTime
 * @Date 2019/6/11 11:19
 */
public class MD5Util {
    /**
     * MD5方法
     *
     * @param username   账号
     * @param password   密码
     * @return 密文
     * @throws Exception
     */
    public static String md5(String username, String password) throws Exception {
        return DigestUtils.md5Hex(username + password);
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

    /**
     * MD5验证方法
     *
     * @param inStr 密文
     * @return String
     */
    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

}
