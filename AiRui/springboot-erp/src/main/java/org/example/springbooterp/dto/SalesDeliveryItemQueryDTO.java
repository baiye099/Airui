package org.example.springbooterp.dto;

import lombok.Data;

@Data
public class SalesDeliveryItemQueryDTO extends BaseDTO {
    private Long deliveryId;
    private Long orderItemId;
    private Long productId;
    private String productCode;
    private String productName;
    private String batchNo;
    private String remark;
}
