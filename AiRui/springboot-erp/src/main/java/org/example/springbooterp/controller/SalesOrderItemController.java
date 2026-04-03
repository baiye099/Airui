package org.example.springbooterp.controller;

import org.example.springbooterp.entity.SalesOrderItem;
import org.example.springbooterp.mapper.SalesOrderItemMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sales-order-item")
public class SalesOrderItemController {

    @Resource
    private SalesOrderItemMapper salesOrderItemMapper;

    @GetMapping
    public List<SalesOrderItem> list() {
        return salesOrderItemMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public SalesOrderItem getById(@PathVariable Long id) {
        return salesOrderItemMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody SalesOrderItem salesOrderItem) {
        return salesOrderItemMapper.insert(salesOrderItem) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SalesOrderItem salesOrderItem) {
        salesOrderItem.setId(id);
        return salesOrderItemMapper.updateById(salesOrderItem) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return salesOrderItemMapper.deleteById(id) > 0;
    }
}
