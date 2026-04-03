package org.example.springbooterp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("purchase_order")
public class PurchaseOrder {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private Long supplierId;
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
    private Long createUserId;
    private Long auditUserId;
    private Date auditTime;
    private Date createTime;
    private Date updateTime;
}
