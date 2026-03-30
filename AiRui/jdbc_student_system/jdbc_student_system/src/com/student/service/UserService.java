package com.student.service;

import com.student.dao.UserDAO; // 自定义数据访问类，用户数据操作
import com.student.model.User; // 自定义实体类，用户信息
import com.student.util.PasswordUtil; // 自定义工具类，密码加密
import com.student.util.ValidationUtil; // 自定义工具类，数据验证

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 用户服务类
 * 继承自BaseService，实现用户相关的业务逻辑
 */
public class UserService extends BaseService {

    private UserDAO userDAO = new UserDAO(); // UserDAO实例

    /**
     * 用户登录
     * 来源：自定义方法
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User login(String username, String password) throws SQLException {
        if (!ValidationUtil.isValidUsername(username)) {
            throw new IllegalArgumentException("用户名格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidPassword(password)) {
            throw new IllegalArgumentException("密码格式不正确"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            User user = userDAO.findByUsername(conn, username); // findByUsername方法：UserDAO类（自定义）的方法
            if (user == null) {
                return null;
            }
            if (user.getStatus() != 1) {
                throw new IllegalStateException("用户已被禁用"); // IllegalStateException：JDK官方异常类
            }
            if (PasswordUtil.checkPassword(password, user.getPassword())) { // checkPassword方法：PasswordUtil类（自定义）的方法
                return user;
            }
            return null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 添加用户
     * 来源：自定义方法
     * @param user 用户对象
     * @param plainPassword 明文密码
     * @return 是否添加成功
     * @throws SQLException SQL异常
     */
    public boolean addUser(User user, String plainPassword) throws SQLException {
        validateUser(user, false);
        if (!ValidationUtil.isValidPassword(plainPassword)) {
            throw new IllegalArgumentException("密码格式不正确（6-50位）"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (userDAO.existsByUsername(conn, user.getUsername())) { // existsByUsername方法：UserDAO类（自定义）的方法
                throw new IllegalArgumentException("用户名已存在"); // IllegalArgumentException：JDK官方异常类
            }
            String hashedPassword = PasswordUtil.hashPassword(plainPassword); // hashPassword方法：PasswordUtil类（自定义）的方法
            user.setPassword(hashedPassword); // setPassword方法：User类（自定义）的方法
            Integer userId = userDAO.insert(conn, user); // insert方法：UserDAO类（自定义）的方法
            return userId != null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新用户信息
     * 来源：自定义方法
     * @param user 用户对象
     * @return 是否更新成功
     * @throws SQLException SQL异常
     */
    public boolean updateUser(User user) throws SQLException {
        validateUser(user, true);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (userDAO.existsByUsernameExcludeId(conn, user.getUsername(), user.getUserId())) { // existsByUsernameExcludeId方法：UserDAO类（自定义）的方法
                throw new IllegalArgumentException("用户名已存在"); // IllegalArgumentException：JDK官方异常类
            }
            int result = userDAO.update(conn, user); // update方法：UserDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新密码
     * 来源：自定义方法
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否更新成功
     * @throws SQLException SQL异常
     */
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) throws SQLException {
        if (!ValidationUtil.isValidPassword(oldPassword)) {
            throw new IllegalArgumentException("旧密码格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidPassword(newPassword)) {
            throw new IllegalArgumentException("新密码格式不正确（6-50位）"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            User user = userDAO.findById(conn, userId); // findById方法：UserDAO类（自定义）的方法
            if (user == null) {
                throw new IllegalArgumentException("用户不存在"); // IllegalArgumentException：JDK官方异常类
            }
            if (!PasswordUtil.checkPassword(oldPassword, user.getPassword())) { // checkPassword方法：PasswordUtil类（自定义）的方法
                throw new IllegalArgumentException("旧密码不正确"); // IllegalArgumentException：JDK官方异常类
            }
            String hashedPassword = PasswordUtil.hashPassword(newPassword); // hashPassword方法：PasswordUtil类（自定义）的方法
            int result = userDAO.updatePassword(conn, userId, hashedPassword); // updatePassword方法：UserDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 删除用户
     * 来源：自定义方法
     * @param userId 用户ID
     * @return 是否删除成功
     * @throws SQLException SQL异常
     */
    public boolean deleteUser(Integer userId) throws SQLException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = userDAO.delete(conn, userId); // delete方法：UserDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据ID获取用户
     * 来源：自定义方法
     * @param userId 用户ID
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User getUserById(Integer userId) throws SQLException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return userDAO.findById(conn, userId); // findById方法：UserDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 获取所有用户
     * 来源：自定义方法
     * @return 用户列表
     * @throws SQLException SQL异常
     */
    public List<User> getAllUsers() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return userDAO.findAll(conn); // findAll方法：UserDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据角色获取用户
     * 来源：自定义方法
     * @param role 角色
     * @return 用户列表
     * @throws SQLException SQL异常
     */
    public List<User> getUsersByRole(String role) throws SQLException {
        if (!ValidationUtil.isValidRole(role)) {
            throw new IllegalArgumentException("角色格式不正确"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return userDAO.findByRole(conn, role); // findByRole方法：UserDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 验证用户信息
     * 来源：自定义方法
     * @param user 用户对象
     * @param isUpdate 是否为更新操作
     */
    private void validateUser(User user, boolean isUpdate) {
        if (isUpdate && user.getUserId() == null) {
            throw new IllegalArgumentException("用户ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidUsername(user.getUsername())) { // isValidUsername方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("用户名格式不正确（3-50位字母、数字、下划线）"); // IllegalArgumentException：JDK官方异常类
        }
        if (user.getRealName() != null && !ValidationUtil.isLengthValid(user.getRealName(), 0, 50)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("真实姓名长度不能超过50位"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidRole(user.getRole())) { // isValidRole方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("角色格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
        if (user.getStatus() == null) {
            user.setStatus(1); // setStatus方法：User类（自定义）的方法
        }
    }
}
