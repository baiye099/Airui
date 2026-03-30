package org.example.springbootmybaties.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户实体类
 */

@TableName("user")
@Data//setter+getter+toString+无参构造
@AllArgsConstructor//全参构造
public class User {

    @TableId(type = IdType.AUTO)
    private Long Id;
    private String name;
    private Integer age;
    private String email;
}
