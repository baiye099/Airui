package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SalesDeliveryQueryDTO extends BaseDTO {
    private String deliveryNo;
    private Long orderId;
    private Long customerId;
    private Date startDeliveryDate;
    private Date endDeliveryDate;
    private Long warehouseId;
    private Integer status;
    private String shippingMethod;
    private String carrier;
    private String remark;
    private Long createUserId;
}
