package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class SalesOrderItemQueryDTO extends BaseDTO {
    private Long orderId;
    private Long productId;
    private String productCode;
    private String productName;
    private String spec;
    private String unit;
    private Integer deliveredQuantityMin;
    private Integer deliveredQuantityMax;
    private Integer remainingQuantityMin;
    private Integer remainingQuantityMax;
    private String remark;
}
