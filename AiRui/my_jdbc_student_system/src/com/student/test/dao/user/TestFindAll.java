package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestFindAll {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try (Connection connection = DBUtil.getConnection()) {
            List<User>  userList = userDAO.findAll(connection);
            userList.forEach((User user)->{
                System.out.println(user);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
