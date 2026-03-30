package com.student.test.service.user;

import com.student.model.User;
import com.student.service.UserService;

public class TestAdd {
    public static void main(String[] args) {
        UserService userService=new UserService();
        User user=new User();
        user.setUsername("3");
        user.setPassword("123456");
        user.setRealName("1");
        user.setRole("teacher");
try {
    if (userService.addUser(user)){
        System.out.println("添加成功");
        System.out.println("用户ID："+user.getUserId());
    }
} catch (Exception e) {
    System.out.println("添加失败");
    e.printStackTrace();
}
    }
}
