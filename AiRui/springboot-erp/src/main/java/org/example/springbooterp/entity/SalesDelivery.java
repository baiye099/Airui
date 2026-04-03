package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("sales_delivery")
public class SalesDelivery {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deliveryNo;
    private Long orderId;
    private Long customerId;
    private Date deliveryDate;
    private Long warehouseId;
    private Integer totalQuantity;
    private Double totalAmount;
    private Integer status;
    private String shippingMethod;
    private String trackingNo;
    private String carrier;
    private String remark;
    private Long createUserId;
    private Long confirmUserId;
    private Date confirmTime;
    private Date createTime;
    private Date updateTime;
}
