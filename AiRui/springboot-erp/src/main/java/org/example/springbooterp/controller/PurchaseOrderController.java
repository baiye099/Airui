package org.example.springbooterp.controller;

import org.example.springbooterp.entity.PurchaseOrder;
import org.example.springbooterp.mapper.PurchaseOrderMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-order")
public class PurchaseOrderController {

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @GetMapping
    public List<PurchaseOrder> list() {
        return purchaseOrderMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public PurchaseOrder getById(@PathVariable Long id) {
        return purchaseOrderMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderMapper.insert(purchaseOrder) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrder.setId(id);
        return purchaseOrderMapper.updateById(purchaseOrder) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return purchaseOrderMapper.deleteById(id) > 0;
    }
}
