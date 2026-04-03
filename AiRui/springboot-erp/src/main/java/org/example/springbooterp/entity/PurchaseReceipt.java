package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("purchase_receipt")
public class PurchaseReceipt {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String receiptNo;
    private Long orderId;
    private Long supplierId;
    private Date receiptDate;
    private Long warehouseId;
    private Integer totalQuantity;
    private Double totalAmount;
    private Integer status;
    private String remark;
    private Long createUserId;
    private Long confirmUserId;
    private Date confirmTime;
    private Date createTime;
    private Date updateTime;
}
