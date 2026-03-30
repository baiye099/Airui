package com.student.service;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.PasswordUtil;
import com.student.util.ValidationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户的业务类
 */
public class UserService extends BaseService {//领导
    /**
     * 上下层组合式写法，下层作为上层的成员变量，方便上层调用下层
     * 将UserDAO定义为UserService的成员变量
     * 此时相当在UserService的全局创建了一个UserDAO对象
     * 业务层所有的方法都可以用这个对象调用DAO方法
     */


    private UserDAO userDAO = new UserDAO();//下属，可以有一个或者多个下属成员

    /**
     * 用户登录的业务方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     * @throws SQLException 异常继续向上抛
     */
    public User login(String username, String password) throws SQLException {
        if (!ValidationUtil.isValidUsername(username)) {
            throw new IllegalArgumentException("用户名格式不正确");
        }
        if (!ValidationUtil.isValidPassword(password)) {
            //抛出异常给上层
            throw new IllegalArgumentException("密码格式不正确");
        }
        Connection connection = null;
        try {
            connection = getConnection();//等同于DBUtil.getConnection（）
            //方法内部不需要创建userDAO，直接使用全局的userDAO
            //上下层之间的调用关键是：把参数传好，把返回结果接受好
            //上层需要关注下层的方法需要几个参数，顺序，类型
            //上层需要关注下层的方法返回什么（返回int？返回User？返回List<User>？）
            //根据用户名查询了一次数据库，业务层不需要关心下层是怎么查数据库的
            User user = userDAO.findByUserName(connection, username);
            //判断下层的查询寻结果
            if (user == null) {
                return null;//下层返回空，中间层也返回null
                //theow new RuntimeException("用户不存在")

            }
            if (user.getStatus() != 1) {
                throw new RuntimeException("用户已被禁用，请联系管理员");
            }
            //用工具校验密码（明文，密文）
            //明文是上层传来的密码password
            //密文是数据库查出来的密码user.getpassword()
            if (!PasswordUtil.checkPassword(password, user.getPassword())) {
                //校验通过，返回user对象
                throw new RuntimeException("密码错误");
            }
            //上层拿到user不是null，且没有发送异常表明，登录成功
            return user;
        } finally {
            closeConnection(connection);//将conn写在上面，否则finally看不到
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户数据模型对象
     * @return
     * @throws Exception
     */
    public boolean addUser(User user) throws Exception {
        validateUser(user, false);
        Connection connection = null;
        try {
            connection = getConnection();
            //先检查用户名是否以存在
            if (userDAO.existsByUsername(connection, user.getUsername())) {
                throw new RuntimeException("注册失败，用户名已存在");
            }
            //插入
            Integer id = userDAO.insert(connection, user);
            //把返回的id插入到user对象，上层可以在user对象中拿到自增长的id
            user.setUserId(id);
            return true;//表示成功,发生异常表示失败
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 校验用户数据
     *
     * @param user     user
     * @param isUpdate isUpate true表示更新，false表示添加
     */
    private void validateUser(User user, boolean isUpdate) {
        //更新时ID不能为null
        if (isUpdate && user.getUserId() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if (ValidationUtil.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("用户名格式错误");
        }
        if (!ValidationUtil.isValidUsername(user.getPassword())) {
            throw new IllegalArgumentException("密码格式错误");
        }
        if (!ValidationUtil.isValidRole(user.getRole())) {
            throw new IllegalArgumentException("角色格式错误");
        }
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
    }

    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) throws SQLException {
        if (!ValidationUtil.isValidPassword(oldPassword)) {
            throw new IllegalArgumentException("旧密码格式不正确");
        }
        if (!ValidationUtil.isValidPassword(newPassword)) {
            throw new IllegalArgumentException("新密码格式不正确（6-50位）");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            User user = userDAO.findByID(connection, userId);
            if (user == null) {
                throw new IllegalArgumentException("用户不存在");
            }
            if (!PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
                throw new IllegalArgumentException("旧密码不正确");
            }
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            int result = userDAO.updatePassword(connection, hashedPassword, userId);
            return result > 0;
        } finally {
            closeConnection(connection);
        }
    }

    public boolean updateUser(User user) throws SQLException {
        validateUser(user, true);
        Connection connection = null;
        try {
            connection = getConnection();
            if (userDAO.existsByUsernameExcludeId(connection, user.getUsername(), user.getUserId())) {
                throw new IllegalArgumentException("用户名已存在");
            }
            int result = userDAO.update(connection, user);
            return result > 0;//表示调用成功
        } finally {
            closeConnection(connection);
        }
    }

    public boolean delete(Integer userId) throws SQLException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        Connection connection = null;
        try {
            connection = getConnection();
            int result = userDAO.delete(connection, userId);
            return result > 0;
        } finally {
            closeConnection(connection);
        }
    }

    public User FindById(Integer userId) throws SQLException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        Connection connection = null;
        try {
            connection = getConnection();
            return userDAO.findByID(connection, userId);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> FindAll() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            return userDAO.findAll(connection);
        } finally {
            closeConnection(connection);
        }

    }
}
