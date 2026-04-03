package org.example.springbooterp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PurchaseReceiptItemQueryDTO extends BaseDTO {
    private Long receiptId;
    private Long orderItemId;
    private Long productId;
    private String productCode;
    private String productName;
    private String batchNo;
    private Date startProductionDate;
    private Date endProductionDate;
    private Date startExpiryDate;
    private Date endExpiryDate;
    private String remark;
}
