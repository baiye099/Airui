package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("supplier")
public class Supplier {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String supplierCode;
    
    private String supplierName;
    
    private String contactPerson;
    
    private String phone;
    
    private String email;
    
    private String address;
    
    private String taxNumber;
    
    private String bankName;
    
    private String bankAccount;
    
    private BigDecimal creditLimit;
    
    private Integer paymentDays;
    
    private Integer supplierType;
    
    private Integer status;
    
    private String remark;
    
    private Long createUserId;
    
    private Long updateUserId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}