package com.student.test.service.user;


import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class TestUpdatePassword {
    public static void main(String[] args) {
        UserService userService = new UserService();


        try {
            if (userService.updatePassword(8, "123456","123456"))
                System.out.println("更新成功");

        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        }
    }
}

