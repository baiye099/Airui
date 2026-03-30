package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class TestLogin {
    public static void main(String[] args) {
        //创建被测试的对象
        //此时创建userService对象，同时会创建它的下层成员userDAO对象
        UserService userService=new UserService();
        try {
            User user= userService.login("admin","123456");
            if (user!=null){
                System.out.println("登录成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
