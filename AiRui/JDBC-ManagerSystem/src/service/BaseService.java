package service;

import java.sql.Connection;
import java.sql.SQLException;
import util.DBUtil;

public abstract class BaseService {
    /**
     * 获取数据库连接
     * @return 数据库连接
     * @throws SQLException SQL异常
     */
    protected Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    /**
     * 开始事务
     * @param conn 数据库连接
     * @throws SQLException SQL异常
     */
    protected void beginTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }

    /**
     * 提交事务
     * @param conn 数据库连接
     */
    protected void commitTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 回滚事务
     * @param conn 数据库连接
     */
    protected void rollbackTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库连接
     * @param conn 数据库连接
     */
    protected void closeConnection(Connection conn) {
        DBUtil.close(conn);
    }
}