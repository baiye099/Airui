package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseOrderQueryDTO extends BaseDTO {
    private String orderNo;
    private Long supplierId;
    private Date startOrderDate;
    private Date endOrderDate;
    private Date startExpectedDate;
    private Date endExpectedDate;
    private Integer status;
    private Integer auditStatus;
    private String shippingMethod;
    private String remark;
    private Long createUserId;
}
