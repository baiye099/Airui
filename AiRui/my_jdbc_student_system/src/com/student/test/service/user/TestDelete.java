package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        UserService userService=new UserService();
        try {

            if (userService.delete(8)){
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        }
    }
}
