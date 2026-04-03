package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseReceiptItemUpdateDTO {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;
    private String batchNo;
    private Date productionDate;
    private Date expiryDate;
    private String remark;
    private Long updateUserId;
}
