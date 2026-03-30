package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO extends BaseDAO<User> {
    /**
     * 根据用户名查找用户
     * @param conn 数据库连接
     * @param username 用户名
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User findByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        return queryOne(conn, sql, this::mapRow, username);
    }

    /**
     * 根据ID查找用户
     * @param conn 数据库连接
     * @param id 用户ID
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User findById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return queryOne(conn, sql, this::mapRow, id);
    }

    /**
     * 查询所有用户
     * @param conn 数据库连接
     * @return 用户列表
     * @throws SQLException SQL异常
     */
    public java.util.List<User> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM user";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 插入用户
     * @param conn 数据库连接
     * @param user 用户对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int insert(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, real_name, role, status) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(conn, sql, user.getUsername(), user.getPassword(), user.getRealName(), user.getRole(), user.getStatus());
    }

    /**
     * 更新用户
     * @param conn 数据库连接
     * @param user 用户对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, password = ?, real_name = ?, role = ?, status = ? WHERE user_id = ?";
        return executeUpdate(conn, sql, user.getUsername(), user.getPassword(), user.getRealName(), user.getRole(), user.getStatus(), user.getId());
    }

    /**
     * 删除用户
     * @param conn 数据库连接
     * @param id 用户ID
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        return executeUpdate(conn, sql, id);
    }

    /**
     * 结果集映射到用户对象
     * @param rs 结果集
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRealName(rs.getString("real_name"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getInt("status"));
        user.setCreateTime(rs.getTimestamp("create_time"));
        user.setUpdateTime(rs.getTimestamp("update_time"));
        return user;
    }
}