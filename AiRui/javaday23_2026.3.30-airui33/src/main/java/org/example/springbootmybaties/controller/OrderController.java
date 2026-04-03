package org.example.springbootmybaties.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springbootmybaties.dto.OrderQueryDTO;
import org.example.springbootmybaties.dto.OrderUpdateDTO;
import org.example.springbootmybaties.entity.Order;
import org.example.springbootmybaties.mapper.OrderMapper;
import org.example.springbootmybaties.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;//组合mapper

    @PostMapping("/")
    public Object createOrder(Order order) {
        // 校验客户端传过来的订单参数
        if (order.getUserId() == null) {
            return "订单的用户ID不能为空";
        }
        if (!StringUtils.hasText(order.getProductName())) {
            return "订单的商品名称不能为空";
        }
        if (order.getOrderStatus() < 0 || order.getOrderStatus() > 5) {
            return "非法订单状态";
        }
        if (order.getPayType() < 1 || order.getPayType() > 3) {
            return "非法的支付类型";
        }
        if (!StringUtils.hasText(order.getPhone())) {
            return "订单的手机号码不能为空";
        }
        if (order.getTotalAmount().intValue() < 0) {
            return "支付金额不能小于0";
        }
        if (!StringUtils.hasText(order.getAddress())) {
            return "订单的地址不能为空";
        }

        // 订单流水号orderNo是服务器生成【时间戳+UUID】
        order.setOrderNo(UUIDUtil.random());
        // 订单创建时间是服务器生成
        order.setCreateTime(LocalDateTime.now());
        // 插入订单
        orderMapper.insert(order);
        return order;


    }

    @DeleteMapping("/{id}")
    public Object deleteOrder(@PathVariable("id") Long id) {
        orderMapper.deleteById(id);
        return "delete success";
    }

    @DeleteMapping("/batch-delete")
    public Object batchDeleteOrders(Long[] ids) {
        orderMapper.deleteByIds(List.of(ids));
        return "delete success";
    }

    /**
     * TODO
     * @param dto
     * @return
     */
    @PutMapping("/")
    public Object updateOrder(OrderUpdateDTO dto) {
        LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Order::getId, dto.getId());
        orderMapper.update(null, updateWrapper);
        return null;
    }

    @GetMapping("/{id}")
    public Object getOrder(@PathVariable("id") Long id) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getId, id);
        Order order = orderMapper.selectOne(queryWrapper);
        return order;
        // 根据id查询订单
    }


    /**
     * TODO
     * @param dto
     * @return
     */
    @GetMapping("/")
    public Object getOrders(OrderQueryDTO dto) {
        IPage<Order> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        // 条件构造器
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        // 按创建时间倒序
        wrapper.orderByDesc(Order::getCreateTime);

        return orderMapper.selectPage(page, wrapper);
        // 组合条件 + 分页查询订单
        return null;
    }

}
