package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SalesDeliveryUpdateDTO {
    private Long id;
    private Date deliveryDate;
    private Long warehouseId;
    private Integer totalQuantity;
    private Double totalAmount;
    private Integer status;
    private String shippingMethod;
    private String trackingNo;
    private String carrier;
    private String remark;
    private Long confirmUserId;
    private Date confirmTime;
    private Long updateUserId;
}
