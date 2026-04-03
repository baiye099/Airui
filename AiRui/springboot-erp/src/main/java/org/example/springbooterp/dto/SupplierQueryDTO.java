package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class SupplierQueryDTO extends BaseDTO {
    private String supplierCode;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private Integer supplierType;
    private Integer status;
    private String address;
    private String remark;
}
