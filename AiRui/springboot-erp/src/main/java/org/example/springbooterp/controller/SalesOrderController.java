package org.example.springbooterp.controller;

import org.example.springbooterp.entity.SalesOrder;
import org.example.springbooterp.mapper.SalesOrderMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sales-order")
public class SalesOrderController {

    @Resource
    private SalesOrderMapper salesOrderMapper;

    @GetMapping
    public List<SalesOrder> list() {
        return salesOrderMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public SalesOrder getById(@PathVariable Long id) {
        return salesOrderMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody SalesOrder salesOrder) {
        return salesOrderMapper.insert(salesOrder) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SalesOrder salesOrder) {
        salesOrder.setId(id);
        return salesOrderMapper.updateById(salesOrder) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return salesOrderMapper.deleteById(id) > 0;
    }
}
