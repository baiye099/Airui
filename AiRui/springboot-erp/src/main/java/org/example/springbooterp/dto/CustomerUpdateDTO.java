package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class CustomerUpdateDTO {
    private Long id;
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
    private Long updateUserId;
}
