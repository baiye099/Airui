package com.student.dao;

import com.student.util.DBUtil; // 自定义工具类，用于数据库连接管理

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.PreparedStatement; // JDK官方类，预编译SQL语句
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.ArrayList; // JDK官方类，动态数组
import java.util.List; // JDK官方类，列表接口

/**
 * DAO层基类
 * 提供通用的数据库操作方法
 * @param <T> 泛型参数，表示实体类类型
 */
public abstract class BaseDAO<T> {

    /**
     * 结果集处理器接口
     * 用于处理单行结果集
     * @param <T> 泛型参数，表示处理结果类型
     */
    protected interface ResultSetHandler<T> {
        /**
         * 处理结果集
         * 来源：自定义方法
         * @param rs 结果集
         * @return 处理后的对象
         * @throws SQLException SQL异常
         */
        T handle(ResultSet rs) throws SQLException;
    }

    /**
     * 行映射器接口
     * 用于将结果集行映射为实体对象
     * @param <T> 泛型参数，表示映射结果类型
     */
    protected interface RowMapper<T> {
        /**
         * 映射结果集行
         * 来源：自定义方法
         * @param rs 结果集
         * @return 映射后的实体对象
         * @throws SQLException SQL异常
         */
        T mapRow(ResultSet rs) throws SQLException;
    }

    /**
     * 执行更新操作（插入、更新、删除）
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param params 参数数组
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    protected int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql); // prepareStatement方法：Connection类（JDK官方）的方法
            setParams(pstmt, params);
            return pstmt.executeUpdate(); // executeUpdate方法：PreparedStatement类（JDK官方）的方法
        } finally {
            DBUtil.close(pstmt, null); // close方法：DBUtil类（自定义）的方法
        }
    }

    /**
     * 执行插入操作并返回生成的主键
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param params 参数数组
     * @return 生成的主键
     * @throws SQLException SQL异常
     */
    protected Integer executeInsert(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // prepareStatement方法：Connection类（JDK官方）的方法
            setParams(pstmt, params);
            int affectedRows = pstmt.executeUpdate(); // executeUpdate方法：PreparedStatement类（JDK官方）的方法
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys(); // getGeneratedKeys方法：PreparedStatement类（JDK官方）的方法
                if (rs.next()) { // next方法：ResultSet类（JDK官方）的方法
                    return rs.getInt(1); // getInt方法：ResultSet类（JDK官方）的方法
                }
            }
            return null;
        } finally {
            DBUtil.close(pstmt, rs); // close方法：DBUtil类（自定义）的方法
        }
    }

    /**
     * 查询单个对象
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param handler 结果集处理器
     * @param params 参数数组
     * @return 查询结果对象
     * @throws SQLException SQL异常
     */
    protected T queryOne(Connection conn, String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql); // prepareStatement方法：Connection类（JDK官方）的方法
            setParams(pstmt, params);
            rs = pstmt.executeQuery(); // executeQuery方法：PreparedStatement类（JDK官方）的方法
            if (rs.next()) { // next方法：ResultSet类（JDK官方）的方法
                return handler.handle(rs);
            }
            return null;
        } finally {
            DBUtil.close(pstmt, rs); // close方法：DBUtil类（自定义）的方法
        }
    }

    /**
     * 查询对象列表
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param rowMapper 行映射器
     * @param params 参数数组
     * @return 查询结果列表
     * @throws SQLException SQL异常
     */
    protected List<T> queryList(Connection conn, String sql, RowMapper<T> rowMapper, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql); // prepareStatement方法：Connection类（JDK官方）的方法
            setParams(pstmt, params);
            rs = pstmt.executeQuery(); // executeQuery方法：PreparedStatement类（JDK官方）的方法
            List<T> list = new ArrayList<>(); // ArrayList构造方法：ArrayList类（JDK官方）的方法
            while (rs.next()) { // next方法：ResultSet类（JDK官方）的方法
                list.add(rowMapper.mapRow(rs)); // add方法：List接口（JDK官方）的方法
            }
            return list;
        } finally {
            DBUtil.close(pstmt, rs); // close方法：DBUtil类（自定义）的方法
        }
    }

    /**
     * 查询记录数量
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param params 参数数组
     * @return 记录数量
     * @throws SQLException SQL异常
     */
    protected int queryCount(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql); // prepareStatement方法：Connection类（JDK官方）的方法
            setParams(pstmt, params);
            rs = pstmt.executeQuery(); // executeQuery方法：PreparedStatement类（JDK官方）的方法
            if (rs.next()) { // next方法：ResultSet类（JDK官方）的方法
                return rs.getInt(1); // getInt方法：ResultSet类（JDK官方）的方法
            }
            return 0;
        } finally {
            DBUtil.close(pstmt, rs); // close方法：DBUtil类（自定义）的方法
        }
    }

    /**
     * 设置预编译语句参数
     * 来源：自定义方法
     * @param pstmt 预编译语句
     * @param params 参数数组
     * @throws SQLException SQL异常
     */
    private void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]); // setObject方法：PreparedStatement类（JDK官方）的方法
            }
        }
    }
}
