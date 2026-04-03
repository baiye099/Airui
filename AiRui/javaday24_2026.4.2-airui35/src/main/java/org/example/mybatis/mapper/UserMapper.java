package org.example.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.mybatis.entity.User;

public interface UserMapper {
    int insert(@Param("u") User user);
}
