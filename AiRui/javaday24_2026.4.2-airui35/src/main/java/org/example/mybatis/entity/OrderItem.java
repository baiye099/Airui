package org.example.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

//如果是自己写sql，这个TableName注解可以不用指定
//因为不需要告诉框架表名是什么，sql中的表名是我们自己写
//完全不用BaseMapper，可以不写这个注解
//单表的用BaseMapper，多表查询自己写，还是要给这个注解
@TableName("t_order_item")
@Data
public class OrderItem {
    @TableId(type = IdType.AUTO)
    //订单明细id
    private Long id;
    //订单id
    private Long orderId;
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //购买数量
    private Integer productNum;
}
