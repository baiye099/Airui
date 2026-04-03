package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class PurchaseOrderItemUpdateDTO {
    private Long id;
    private Long productId;
    private String productCode;
    private String productName;
    private String spec;
    private String unit;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;
    private Double taxRate;
    private Double taxAmount;
    private Integer receivedQuantity;
    private Integer remainingQuantity;
    private String remark;
    private Long updateUserId;
}
