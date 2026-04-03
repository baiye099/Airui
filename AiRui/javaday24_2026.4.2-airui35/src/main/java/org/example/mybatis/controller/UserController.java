package org.example.mybatis.controller;

import org.example.mybatis.entity.User;
import org.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
    UserMapper userMapper;
@PostMapping("/")
    public Object createUser(@RequestBody User user){
    userMapper.insert(user);
    return user;
}
}
