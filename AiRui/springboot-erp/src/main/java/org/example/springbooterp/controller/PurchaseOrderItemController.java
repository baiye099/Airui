package org.example.springbooterp.controller;

import org.example.springbooterp.entity.PurchaseOrderItem;
import org.example.springbooterp.mapper.PurchaseOrderItemMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-order-item")
public class PurchaseOrderItemController {

    @Resource
    private PurchaseOrderItemMapper purchaseOrderItemMapper;

    @GetMapping
    public List<PurchaseOrderItem> list() {
        return purchaseOrderItemMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public PurchaseOrderItem getById(@PathVariable Long id) {
        return purchaseOrderItemMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemMapper.insert(purchaseOrderItem) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItem.setId(id);
        return purchaseOrderItemMapper.updateById(purchaseOrderItem) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return purchaseOrderItemMapper.deleteById(id) > 0;
    }
}
