package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByName {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try (Connection connection = DBUtil.getConnection()) {
            User user = userDAO.findByUserName(connection, "admin");
            System.out.println(user.getUserId());
            System.out.println(user.getUsername());
            System.out.println(user.getRealName());
            System.out.println(user.getRole());
            System.out.println(user.getStatus());
            System.out.println(user.getPassword());
            System.out.println(user.getCreateTime());
            System.out.println(user.getUpdateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
