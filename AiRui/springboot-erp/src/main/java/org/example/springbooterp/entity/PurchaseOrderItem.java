package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("purchase_order_item")
public class PurchaseOrderItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long orderId;
    private Long productId;
    private String productCode;
    private String productName;
    private String spec;
    private String unit;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;
    private Double taxRate;
    private Double taxAmount;
    private Integer receivedQuantity;
    private Integer remainingQuantity;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
