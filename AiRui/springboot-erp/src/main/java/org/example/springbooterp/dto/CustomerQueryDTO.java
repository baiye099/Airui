package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class CustomerQueryDTO extends BaseDTO {
    private String customerCode;
    private String customerName;
    private String contactPerson;
    private String phone;
    private Integer customerType;
    private Integer customerLevel;
    private Integer status;
    private String address;
    private String remark;
}
