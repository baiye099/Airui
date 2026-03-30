package org.example.springbootmybaties.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.springbootmybaties.entity.User;

/**
 * UserMapper 接口用于代替UserDAO
 * 用于操作user表的增删改查
 * 需要继承mybatis框架的BaseMapper接口
 * 指定实体类泛型
 */
public interface UserMapper extends BaseMapper<User> {
}
