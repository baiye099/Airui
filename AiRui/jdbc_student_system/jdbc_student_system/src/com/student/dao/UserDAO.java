package com.student.dao;

import com.student.model.User; // 自定义实体类，用户信息

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 用户数据访问类
 * 继承自BaseDAO，实现用户相关的数据库操作
 */
public class UserDAO extends BaseDAO<User> {

    /**
     * 插入用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param user 用户信息对象
     * @return 生成的用户ID
     * @throws SQLException SQL异常
     */
    public Integer insert(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, real_name, role, status) VALUES (?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, user.getUsername(), user.getPassword(), user.getRealName(), user.getRole(), user.getStatus()); // executeInsert方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param user 用户信息对象
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, real_name = ?, role = ?, status = ? WHERE user_id = ?";
        return executeUpdate(conn, sql, user.getUsername(), user.getRealName(), user.getRole(), user.getStatus(), user.getUserId()); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新用户密码
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param userId 用户ID
     * @param password 新密码
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int updatePassword(Connection conn, Integer userId, String password) throws SQLException {
        String sql = "UPDATE user SET password = ? WHERE user_id = ?";
        return executeUpdate(conn, sql, password, userId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 删除用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param userId 用户ID
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer userId) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        return executeUpdate(conn, sql, userId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据ID查询用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param userId 用户ID
     * @return 用户信息对象
     * @throws SQLException SQL异常
     */
    public User findById(Connection conn, Integer userId) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return queryOne(conn, sql, this::mapRow, userId); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据用户名查询用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param username 用户名
     * @return 用户信息对象
     * @throws SQLException SQL异常
     */
    public User findByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        return queryOne(conn, sql, this::mapRow, username); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 查询所有用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @return 用户信息列表
     * @throws SQLException SQL异常
     */
    public List<User> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM user ORDER BY user_id";
        return queryList(conn, sql, this::mapRow); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据角色查询用户信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param role 角色
     * @return 用户信息列表
     * @throws SQLException SQL异常
     */
    public List<User> findByRole(Connection conn, String role) throws SQLException {
        String sql = "SELECT * FROM user WHERE role = ? ORDER BY user_id";
        return queryList(conn, sql, this::mapRow, role); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查用户名是否存在
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param username 用户名
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        return queryCount(conn, sql, username) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查用户名是否存在（排除指定ID）
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param username 用户名
     * @param excludeId 排除的用户ID
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByUsernameExcludeId(Connection conn, String username, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND user_id != ?";
        return queryCount(conn, sql, username, excludeId) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 结果集行映射
     * 来源：自定义方法
     * @param rs 结果集
     * @return 用户信息对象
     * @throws SQLException SQL异常
     */
    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User(); // User构造方法：User类（自定义）的方法
        user.setUserId(rs.getInt("user_id")); // getInt方法：ResultSet类（JDK官方）的方法
        user.setUsername(rs.getString("username")); // getString方法：ResultSet类（JDK官方）的方法
        user.setPassword(rs.getString("password")); // getString方法：ResultSet类（JDK官方）的方法
        user.setRealName(rs.getString("real_name")); // getString方法：ResultSet类（JDK官方）的方法
        user.setRole(rs.getString("role")); // getString方法：ResultSet类（JDK官方）的方法
        user.setStatus(rs.getInt("status")); // getInt方法：ResultSet类（JDK官方）的方法
        user.setCreateTime(rs.getTimestamp("create_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        user.setUpdateTime(rs.getTimestamp("update_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        return user;
    }
}
