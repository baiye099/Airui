package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class SupplierUpdateDTO {
    private Long id;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String taxNumber;
    private String bankName;
    private String bankAccount;
    private Double creditLimit;
    private Integer paymentDays;
    private Integer supplierType;
    private Integer status;
    private String remark;
    private Long updateUserId;
}
