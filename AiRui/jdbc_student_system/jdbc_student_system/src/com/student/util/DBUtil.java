package com.student.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接工具类
 * 功能：管理数据库连接的获取和关闭
 * 来源：自定义封装
 */
public class DBUtil {
    
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    static {
        try {
            // 加载配置文件（自定义封装方法）
            Properties props = new Properties();
            // getResourceAsStream：JDK官方方法，从类路径加载资源
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("config/db.properties");
            if (is == null) {
                throw new RuntimeException("找不到数据库配置文件");
            }
            // load：Properties类官方方法，加载配置文件
            props.load(is);
            
            driver = props.getProperty("db.driver");
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
            
            // Class.forName：JDK官方方法，加载数据库驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("加载数据库驱动失败", e);
        }
    }
    
    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException SQL异常
     * 来源：自定义封装方法，内部使用DriverManager官方方法
     */
    public static Connection getConnection() throws SQLException {
        // getConnection：DriverManager官方静态方法，获取数据库连接
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * 关闭数据库资源
     * @param conn 连接对象
     * @param stmt 语句对象
     * @param rs 结果集对象
     * 来源：自定义封装方法
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                // close：ResultSet官方方法，关闭结果集
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                // close：Statement官方方法，关闭语句
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                // close：Connection官方方法，关闭连接
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 关闭数据库资源（重载方法）
     * @param conn 连接对象
     * @param stmt 语句对象
     * 来源：自定义封装方法
     */
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }
    
    /**
     * 关闭数据库资源（重载方法）
     * @param conn 连接对象
     * 来源：自定义封装方法
     */
    public static void close(Connection conn) {
        close(conn, null, null);
    }
    
    /**
     * 关闭数据库资源（重载方法）
     * @param pstmt 预处理语句对象
     * @param rs 结果集对象
     * 来源：自定义封装方法
     */
    public static void close(PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                // close：ResultSet官方方法，关闭结果集
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                // close：PreparedStatement官方方法，关闭预处理语句
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
