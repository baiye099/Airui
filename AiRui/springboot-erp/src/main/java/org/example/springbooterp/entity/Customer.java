package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("customer")
public class Customer {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String customerCode;
    private String customerName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String taxNumber;
    private String bankName;
    private String bankAccount;
    private Double creditLimit;
    private Integer paymentDays;
    private Integer customerType;
    private Integer customerLevel;
    private Integer status;
    private String remark;
    private Long createUserId;
    private Long updateUserId;
    private Date createTime;
    private Date updateTime;
}
