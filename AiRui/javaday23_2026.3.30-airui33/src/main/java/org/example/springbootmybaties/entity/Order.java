package org.example.springbootmybaties.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order_info")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String orderNo;
    private String productName;
    //订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消
    private Integer orderStatus;
    //支付状态：1-支付宝 2-微信支付 3-银行卡
    private Integer payType;
    private BigDecimal totalAmount;
    private String phone;
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
