package org.example.mybatis.mapper;


import org.apache.ibatis.annotations.Param;
import org.example.mybatis.entity.Order;
import org.example.mybatis.entity.User;
import stats.OrderStats;

import java.util.List;

//需要绑定xml不继承BaseMapper
public interface OrderRelationMapper   {
    List<Order> selectOrderListWithOrderItem();
    List<OrderStats>selectOrderStatsGroupByStatus();

}
