package org.example.homework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("student")
@Data
@AllArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Long id;
    private  String  no;
    private  String name;
    private  String  professional;
    private  String sex;
    private  String phone;
}
