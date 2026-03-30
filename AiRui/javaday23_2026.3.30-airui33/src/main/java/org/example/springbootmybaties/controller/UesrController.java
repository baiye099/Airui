package org.example.springbootmybaties.controller;

import org.example.springbootmybaties.entity.User;
import org.example.springbootmybaties.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UesrController {
    @Autowired
    UserMapper userMapper;//组合Mapper

    @GetMapping("/")
    // 调用userMapper去查数据库user表所有数据
    public Object getAllUsers() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @PostMapping("/")
    public Object createUser(@RequestBody User user) {
        //调用插入用户的方法
        userMapper.insert(user);
        return user;
    }
// rest风格的url地址DELETE/api/user/3
/*    @DeleteMapping("/{id}")
    public Object deleterUser(@PathVariable("id") Long id) {
        userMapper.deleteById(id);
        return "delete success";
    }*/

    //传统URL携带参数DELETE /api/user?id=3
    @DeleteMapping("/")
    public Object deleterUser(Long id) {
        userMapper.deleteById(id);
        return "delete success";
    }
    //DeleteMapping("/")只能有一个
   // 批量删除改为：@DeleteMapping("/batch -delete")
    @DeleteMapping("/batch -delete")
    public Object batchDelete(@RequestBody Long[] ids){
        //根据id批量删除的方法，用List.of（）方法把ids数组转为List集合传给方法
        userMapper.deleteByIds(List.of(ids));
        return "delete success";
    }

}
