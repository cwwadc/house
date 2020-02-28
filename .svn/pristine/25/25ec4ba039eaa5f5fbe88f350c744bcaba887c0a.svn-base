package com.msz.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author: cww
 * @date: 2019/7/15 17:52
 */
public class CodeUtils {

    /**
     * 自动生成编号格式：yyyyMMhh+六位位流水号
     */
    public static String createCode(String fieldName, String tableName) {

        String sql = "SELECT MAX(a."+fieldName+") max_code FROM "+tableName+" a";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result;
        String maxCode = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeQuery();
            while (result.next()){
                 maxCode = result.getString("max_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
        return maxCode;
    }

    /*
     * 把10002首位的1去掉的实现方法：
     * @param str
     * @param start
     * @return
     */
    public static String subStr(String str, int start) {
        if (str == null || str.equals("") || str.length() == 0)
            return "";
        if (start < str.length()) {
            return str.substring(start);
        } else {
            return "";
        }

    }


}
