package com.student.service;

import com.student.util.DBUtil; // 自定义工具类，用于数据库连接管理

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常

/**
 * Service层基类
 * 提供通用的数据库连接和事务管理方法
 */
public abstract class BaseService {

    /**
     * 获取数据库连接
     * 来源：自定义方法
     * @return 数据库连接
     * @throws SQLException SQL异常
     */
    protected Connection getConnection() throws SQLException {
        return DBUtil.getConnection(); // getConnection方法：DBUtil类（自定义）的方法
    }

    /**
     * 开始事务
     * 来源：自定义方法
     * @param conn 数据库连接
     * @throws SQLException SQL异常
     */
    protected void beginTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(false); // setAutoCommit方法：Connection类（JDK官方）的方法
        }
    }

    /**
     * 提交事务
     * 来源：自定义方法
     * @param conn 数据库连接
     */
    protected void commitTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.commit(); // commit方法：Connection类（JDK官方）的方法
            } catch (SQLException e) {
                e.printStackTrace(); // printStackTrace方法：Throwable类（JDK官方）的方法
            }
        }
    }

    /**
     * 回滚事务
     * 来源：自定义方法
     * @param conn 数据库连接
     */
    protected void rollbackTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback(); // rollback方法：Connection类（JDK官方）的方法
            } catch (SQLException e) {
                e.printStackTrace(); // printStackTrace方法：Throwable类（JDK官方）的方法
            }
        }
    }

    /**
     * 关闭数据库连接
     * 来源：自定义方法
     * @param conn 数据库连接
     */
    protected void closeConnection(Connection conn) {
        DBUtil.close(conn); // close方法：DBUtil类（自定义）的方法
    }

    /**
     * 设置自动提交
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param autoCommit 是否自动提交
     * @throws SQLException SQL异常
     */
    protected void setAutoCommit(Connection conn, boolean autoCommit) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(autoCommit); // setAutoCommit方法：Connection类（JDK官方）的方法
        }
    }
}
