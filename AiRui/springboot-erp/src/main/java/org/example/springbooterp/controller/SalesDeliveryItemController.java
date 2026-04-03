package org.example.springbooterp.controller;

import org.example.springbooterp.entity.SalesDeliveryItem;
import org.example.springbooterp.mapper.SalesDeliveryItemMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sales-delivery-item")
public class SalesDeliveryItemController {

    @Resource
    private SalesDeliveryItemMapper salesDeliveryItemMapper;

    @GetMapping
    public List<SalesDeliveryItem> list() {
        return salesDeliveryItemMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public SalesDeliveryItem getById(@PathVariable Long id) {
        return salesDeliveryItemMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody SalesDeliveryItem salesDeliveryItem) {
        return salesDeliveryItemMapper.insert(salesDeliveryItem) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SalesDeliveryItem salesDeliveryItem) {
        salesDeliveryItem.setId(id);
        return salesDeliveryItemMapper.updateById(salesDeliveryItem) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return salesDeliveryItemMapper.deleteById(id) > 0;
    }
}
