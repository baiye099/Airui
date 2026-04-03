package org.example.springbooterp.controller;

import org.example.springbooterp.entity.SalesDelivery;
import org.example.springbooterp.mapper.SalesDeliveryMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sales-delivery")
public class SalesDeliveryController {

    @Resource
    private SalesDeliveryMapper salesDeliveryMapper;

    @GetMapping
    public List<SalesDelivery> list() {
        return salesDeliveryMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public SalesDelivery getById(@PathVariable Long id) {
        return salesDeliveryMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody SalesDelivery salesDelivery) {
        return salesDeliveryMapper.insert(salesDelivery) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SalesDelivery salesDelivery) {
        salesDelivery.setId(id);
        return salesDeliveryMapper.updateById(salesDelivery) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return salesDeliveryMapper.deleteById(id) > 0;
    }
}
