package org.example.springboot01.controller;

import org.example.springboot01.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//使用Rest风格的控制器（主流）
@RestController
//绑定指定类的请求地址
@RequestMapping("/users")
public class UserController {
    @GetMapping("/")
    public Object getUsers() {
        //应该返回数据库查出的List
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
//            return "查询用户列表";
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable("id") Integer id) {
        return new User();
    }

    @PostMapping("/")
    public Object createUser() {
        return "创建用户";
    }

    @PutMapping("/{id}")
    public Object updateUser(User user) {
        return "修改用户";
    }

    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable("id") Integer id) {
        return "删除用户" + id;
    }
}

