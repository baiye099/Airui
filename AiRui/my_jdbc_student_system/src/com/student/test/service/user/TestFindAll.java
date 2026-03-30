package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class TestFindAll {
    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            List<User> userList = userService.FindAll();
            userList.forEach((User user)->{
                System.out.println(user);
            });
        } catch (SQLException e) {
            System.out.println("查找失败");
            e.printStackTrace();
        }
    }
}
