package org.example.springbootmybaties.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springbootmybaties.dto.UserDTO;
import org.example.springbootmybaties.dto.UserUpdateDTO;
import org.example.springbootmybaties.entity.User;
import org.example.springbootmybaties.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UesrController {
    @Autowired
    UserMapper userMapper;//组合Mapper

    @GetMapping("/")
    // 调用userMapper去查数据库user表所有数据
    public Object getAllUsers(UserDTO userDTO) {//userDTO复杂参数
        /*//创建条件构造器
        QueryWrapper queryWrapper = new QueryWrapper();
        //指定条件构造规则where age>minAge
        //queryWrapper.gt("age",minAge);
        //情况1：只传了minAge
        queryWrapper.gt(userDTO.getMinAge() != null && userDTO.getMaxAge() == null, "age", userDTO.getMinAge());
        //勤快2：只传了maxAge
        queryWrapper.lt(userDTO.getMinAge() == null && userDTO.getMaxAge() != null, "age", userDTO.getMaxAge());
        //情况3：minAge和maxAge都为空
        queryWrapper.between(userDTO.getMinAge() != null && userDTO.getMaxAge() != null, "age", userDTO.getMinAge(), userDTO.getMaxAge());
        //继续判断namekey不为空的情况
        //字符串判空需要判断2中 null 和“ ”
        //可以使用框架字符串工具类StringUtils.has(字符串)返回boolean
        //where name like %?%
        //用user DTOgetNameKey()填充？
        queryWrapper.like(StringUtils.hasText(userDTO.getNameKey()), "name", userDTO.getNameKey());
        //继续判断emalikeyKey不为空的情况
        queryWrapper.like(StringUtils.hasText(userDTO.getNameKey()), "email", userDTO.getEmailKey());*/


        //创建新式语法（Lambda表达式语法）的条件构造器
        //需要指定一个泛型，泛型用entity而不是dto，泛型的作用是指定方法引用的实体类型
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //传统方法把列名用字符串写死属于【硬编码】【魔法值】
        //User::getAge代替了"age"
        //类名::方法名称为“方法引用”Java8新特性
        List<User> users = userMapper.selectList(queryWrapper);
        queryWrapper.gt(userDTO.getMinAge() != null && userDTO.getMaxAge() == null, User::getAge, userDTO.getMinAge());
        queryWrapper.lt(userDTO.getMinAge() == null && userDTO.getMaxAge() != null, User::getAge, userDTO.getMaxAge());
        queryWrapper.between(userDTO.getMinAge() != null && userDTO.getMaxAge() != null, User::getAge, userDTO.getMinAge(), userDTO.getMaxAge());
        queryWrapper.like(StringUtils.hasText(userDTO.getNameKey()), User::getName, userDTO.getNameKey());
        queryWrapper.like(StringUtils.hasText(userDTO.getEmailKey()), User::getEmail, userDTO.getEmailKey());
        //创建分页器，指定实体类泛型
        //从dto对象中取出current和size作为分页器构造方法的参数
        //让分页器知道查第几页，每页几行

        Page<User> page ;
        if (userDTO.getCurrent()!=null&&userDTO.getSize()!=null){
            page= new Page<>(userDTO.getCurrent(), userDTO.getSize());
        }else {
            page=new Page<>();
        }
        //selectList方法默认是否查询所有数据，不支持分页
        //List<User> page=userMapper.selectList(queryWrapper);
        //selectPage方法支持分页查询，参数1传分页器，参数2传条件构造器
        //并将该方法返回的结果赋给分页器page
        page = userMapper.selectPage(page, queryWrapper);

        return page;
    }

    @GetMapping("/search")
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
    public Object batchDelete(@RequestBody Long[] ids) {
        //根据id批量删除的方法，用List.of（）方法把ids数组转为List集合传给方法
        userMapper.deleteByIds(List.of(ids));
        return "delete success";
    }


    @PutMapping("/")
    public Object updateUser(@RequestBody UserUpdateDTO userDTO) {
        if (userDTO.getId() == null) {
            return "id不允许为空";
        }

        //硬编码方式，更新的列名用字符串写死
        //Lambda表达式方式，更新的列名用方法引用
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, userDTO.getId());
        updateWrapper.set(StringUtils.hasText(userDTO.getName()), User::getName, userDTO.getName());
        updateWrapper.set(userDTO.getAge() != null, User::getAge, userDTO.getAge());
        updateWrapper.set(StringUtils.hasText(userDTO.getEmail()), User::getEmail, userDTO.getEmail());
        userMapper.update(updateWrapper);
        return null;
    }
}
