package com.student.dao;

import com.student.model.User;
import com.student.util.PasswordUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 这是操作user表的DAO类
 * 单一职责原则，遵守：一个DAO类只操作一张表的数据管理
 * DAO子类统一继承  Base DAO作为父类（继承到封装的公共功能，简化开发）
 * 指定泛型<T>=<User>标志这个类中的数据实体模型事User类型
 */

public class UserDAO extends BaseDAO<User> {
    /**
     * 输入一个用户
     *
     * @param connection
     * @param user
     * @return
     * @throws SQLException
     */
    public Integer insert(Connection connection, User user) throws SQLException {
        //子类只需要写sql语句即可
        String sql = "insert into user(username,password,real_name,role)values(?,?,?,?)";
        //sql中4个？，要和user对象中取出4个对象数据传给父类
        //父类完成sql语句预编译，填充？参数，执行sql返回结果的功能
        return super.executeInsert(connection, sql, user.getUsername(), PasswordUtil.hashPassword(user.getPassword()), user.getRealName(), user.getRole());
    }

    public int update(Connection connection, User user) throws SQLException {
        String sql = "UPDATE user SET username=?,real_name=?,role=?,status=? WHERE user_id=?";
        return executeUpdate(connection, sql, user.getUsername(), user.getRealName(), user.getRole(), user.getStatus(), user.getUserId());
    }

    public int updatePassword(Connection connection, String password, Integer userID) throws SQLException {
        String sql = "UPDATE user SET password=?WHERE user_id=?";
        return executeUpdate(connection, sql, password, userID);
    }

    public int delete(Connection connection, Integer userID) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        return executeUpdate(connection, sql, userID);
    }

    /**
     * 用户根据自己的ID查询个人详细资料
     *
     * @param connection 入参1：数据库连接
     * @param userID     入参2：用户ID
     * @return User对象
     * @throws SQLException
     */
    public User findByID(Connection connection, Integer userID) throws SQLException {
        String sql = "SELECT *FROM user WHERE user_id=?";
        //查询一行数据，调用父类的queryOne方法
        //参数3处要传一个回调函数，这个回调函数的作用是告诉父类，怎么将查询到的数据映射为Java对象
        //this：：函数名     是Java新特性，表示传一个函数的地址
        //此处需要先把mapRow函数定义出来
        //惨参数4开始传？需要的参数
        return super.queryOne(connection, sql, this::mapRow, userID);

    }

    /**
     * 根据用户名查询用户信息
     * 根据id查用户对于查询用户详情功能
     * 根据username查用户，用于登录系统
     * 只要是查询，都要复用了mapRow映射函数
     * 来源：自定义方法
     *
     * @param connection
     * @param userName
     * @throws SQLException
     * @return用户信息对象
     */
    public User findByUserName(Connection connection, String userName) throws SQLException {
        String sql = "SELECT *FROM user WHERE username=?";
        //查询一行数据，调用父类的queryOne方法
        //参数3处要传一个回调函数，这个回调函数的作用是告诉父类，怎么将查询到的数据映射为Java对象
        //this：：函数名     是Java新特性，表示传一个函数的地址
        //此处需要先把mapRow函数定义出来
        //惨参数4开始传？需要的参数
        return super.queryOne(connection, sql, this::mapRow, userName);

    }

    /**
     * 查询user所有对象
     * @param connection
     * @return List集合（底层是ArrayList），集合中存储多个User对象
     * @throws SQLException
     */
    public List<User> findAll(Connection connection) throws SQLException{
        String sql = "SELECT *FROM user ORDER BY user_id";
        return queryList(connection,sql,this::mapRow);
    }
    public boolean existsByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        return queryCount(conn, sql, username) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }
    public boolean existsByUsernameExcludeId(Connection conn, String username, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND user_id != ?";
        return queryCount(conn, sql, username, excludeId) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 这个方法 成为“行-对象映射器”
     *
     * @param resultSet 结果集中的数据行
     * @return 用户对象信息
     * @throws SQLException
     */
    private User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        //取出结果集rs中的user_id,存储到user对象中的成员userID中
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setRealName(resultSet.getString("real_name"));
        user.setRole(resultSet.getString("role"));
        user.setStatus(resultSet.getInt("status"));
        user.setCreateTime(resultSet.getDate("create_time"));
        user.setUpdateTime(resultSet.getDate("update_time"));
        return user;
    }


}
