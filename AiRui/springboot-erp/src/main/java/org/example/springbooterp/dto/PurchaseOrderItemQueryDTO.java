package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class PurchaseOrderItemQueryDTO extends BaseDTO {
    private Long orderId;
    private Long productId;
    private String productCode;
    private String productName;
    private String spec;
    private String unit;
    private Integer receivedQuantityMin;
    private Integer receivedQuantityMax;
    private Integer remainingQuantityMin;
    private Integer remainingQuantityMax;
    private String remark;
}
