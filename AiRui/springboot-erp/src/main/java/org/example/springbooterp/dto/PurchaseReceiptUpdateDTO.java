package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseReceiptUpdateDTO {
    private Long id;
    private Date receiptDate;
    private Long warehouseId;
    private Integer totalQuantity;
    private Double totalAmount;
    private Integer status;
    private String remark;
    private Long confirmUserId;
    private Date confirmTime;
    private Long updateUserId;
}
