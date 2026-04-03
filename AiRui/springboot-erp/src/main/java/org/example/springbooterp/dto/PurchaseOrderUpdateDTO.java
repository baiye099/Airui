package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseOrderUpdateDTO {
    private Long id;
    private Date orderDate;
    private Date expectedDate;
    private String deliveryAddress;
    private String contactPerson;
    private String contactPhone;
    private Integer totalQuantity;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double finalAmount;
    private Integer status;
    private Integer auditStatus;
    private String shippingMethod;
    private String trackingNo;
    private Date deliveryDate;
    private String remark;
    private String rejectReason;
    private Long auditUserId;
    private Date auditTime;
    private Long updateUserId;
}
