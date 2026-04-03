package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class SalesDeliveryItemUpdateDTO {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;
    private String batchNo;
    private String remark;
    private Long updateUserId;
}
