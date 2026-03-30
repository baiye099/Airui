package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    /**
     * 执行更新操作（插入、更新、删除）
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param params 参数数组
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    protected int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeUpdate();
        }
    }

    /**
     * 查询单条记录
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param handler 结果集处理器
     * @param params 参数数组
     * @return 查询结果
     * @throws SQLException SQL异常
     */
    protected T queryOne(Connection conn, String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return handler.handle(rs);
                }
                return null;
            }
        }
    }

    /**
     * 查询多条记录
     * @param conn 数据库连接
     * @param sql SQL语句
     * @param rowMapper 行映射器
     * @param params 参数数组
     * @return 查询结果列表
     * @throws SQLException SQL异常
     */
    protected List<T> queryList(Connection conn, String sql, RowMapper<T> rowMapper, Object... params) throws SQLException {
        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rowMapper.mapRow(rs));
                }
            }
        }
        return list;
    }

    /**
     * 设置PreparedStatement的参数
     * @param ps PreparedStatement对象
     * @param params 参数数组
     * @throws SQLException SQL异常
     */
    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 结果集处理器接口
     * @param <T> 处理结果类型
     */
    @FunctionalInterface
    protected interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }

    /**
     * 行映射器接口
     * @param <T> 映射结果类型
     */
    @FunctionalInterface
    protected interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
}