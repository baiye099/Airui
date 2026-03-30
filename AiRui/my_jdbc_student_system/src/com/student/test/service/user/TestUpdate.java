package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("2");
        user.setRealName("2");
        user.setRole("student");
        user.setStatus(1);
        user.setUserId(8);
        user.setPassword("123456");
        try {
            if (userService.updateUser(user))
                System.out.println("更新成功");

        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        }
    }
}
