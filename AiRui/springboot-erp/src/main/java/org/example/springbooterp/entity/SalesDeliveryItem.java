package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("sales_delivery_item")
public class SalesDeliveryItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long deliveryId;
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Double amount;
    private String batchNo;
    private String remark;
    private Date createTime;
}
