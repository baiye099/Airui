package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class TestFindById {
    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            User user = userService.FindById(5);
            System.out.println(user);
        } catch (SQLException e) {
            System.out.println("查找失败");
            e.printStackTrace();
        }
    }
}
