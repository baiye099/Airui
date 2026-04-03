package org.example.mybatis.controller;

import org.example.mybatis.entity.Order;
import org.example.mybatis.mapper.OrderMapper;
import org.example.mybatis.mapper.OrderRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stats.OrderStats;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderRelationMapper orderRelationMapper;
    @GetMapping("/list")
    public Object getAll(){
        List<Order> orderList=orderMapper.selectList(null);
        return orderList;
    }
    @GetMapping("/with-items")

    public Object getAllWithItems(){
        List<Order>orderList=orderRelationMapper.selectOrderListWithOrderItem();
        return orderList;
    }

    @GetMapping("/stats")
    public Object getOrderStats(){
        List<OrderStats> orderStatsList=orderRelationMapper.selectOrderStatsGroupByStatus();
        return orderStatsList;
    }
}
