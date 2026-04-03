package stats;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStats {
    //映射统计结果
    //订单数量
    private Integer orderCount;
    //订单总金额
    private BigDecimal totalAmount;
    //商品总数量
    private Integer totalProductNum;
    private  Integer orderStatus;
}
