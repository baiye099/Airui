package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseReceiptQueryDTO extends BaseDTO {
    private String receiptNo;
    private Long orderId;
    private Long supplierId;
    private Date startReceiptDate;
    private Date endReceiptDate;
    private Long warehouseId;
    private Integer status;
    private String remark;
    private Long createUserId;
}
