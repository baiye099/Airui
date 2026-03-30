package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        UserDAO userDAO=new UserDAO();
        try(Connection connection= DBUtil.getConnection()) {
            User user=new User();
            user.setUsername("admin");
            user.setRealName("系统管理员");
            user.setRole("admin");
            user.setStatus(1);
            user.setUserId(1);
            int rows= userDAO.update(connection,user);
            System.out.println(" 更新"+user+"成功");
            System.out.println("受影响行数"+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
