package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.UserDAO;
import model.User;
import util.PasswordUtil;

public class UserService extends BaseService {
    private UserDAO userDAO = new UserDAO();

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User login(String username, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            User user = userDAO.findByUsername(conn, username);
            if (user != null && user.getStatus() == 1 && PasswordUtil.checkPassword(password, user.getPassword())) {
                return user;
            }
            return null;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 添加用户
     * @param user 用户对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean addUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            // 检查用户名是否已存在
            User existingUser = userDAO.findByUsername(conn, user.getUsername());
            if (existingUser != null) {
                return false;
            }
            // 加密密码
            user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            // 插入用户
            int result = userDAO.insert(conn, user);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 更新用户
     * @param user 用户对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean updateUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            // 检查用户名是否已被其他用户使用
            User existingUser = userDAO.findByUsername(conn, user.getUsername());
            if (existingUser != null && existingUser.getId() != user.getId()) {
                return false;
            }
            // 加密密码
            user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            // 更新用户
            int result = userDAO.update(conn, user);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean deleteUser(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = userDAO.delete(conn, id);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User getUserById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return userDAO.findById(conn, id);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取所有用户
     * @return 用户列表
     * @throws SQLException SQL异常
     */
    public java.util.List<User> getAllUsers() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return userDAO.findAll(conn);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 修改密码
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            User user = userDAO.findById(conn, userId);
            if (user == null) {
                return false;
            }
            // 验证旧密码
            if (!PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
                return false;
            }
            // 更新密码
            user.setPassword(PasswordUtil.hashPassword(newPassword));
            int result = userDAO.update(conn, user);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }
}