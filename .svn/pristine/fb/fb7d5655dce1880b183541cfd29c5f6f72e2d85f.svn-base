package com.msz.util;

import java.sql.*;

public class DbUtil {

    /**
     * 取得数据库的连接
     * @return 一个数据库的连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //初始化驱动类com.microsoft.sqlserver.jdbc.SQLServerDriver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://rm-wz9oepb0lzl8qk065xo.mysql.rds.aliyuncs.com:3306/msz?#useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "HLXJ@1234");
            //该类就在 mysql-connector-java-5.0.8-bin.jar中,如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /*
     * 封装三个关闭方法
	 * @param pstmt
	 */
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
