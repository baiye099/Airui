package org.example.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@TableName("t_order")
@Data
public class Order {
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalAmount;
    private Integer orderStatus;
    private LocalDateTime createTime;
    //用List定义成员用于存储关联的子表的多行数据
    //告诉BaseMapper，表中没有这个列
    //如果没有排除掉，单表自动生成sql中会出现这个列
@TableField(exist = false)

private List<OrderItem> orderItemList;


}
