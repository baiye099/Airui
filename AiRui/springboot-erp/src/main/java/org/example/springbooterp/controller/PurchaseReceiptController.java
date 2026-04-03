package org.example.springbooterp.controller;

import org.example.springbooterp.entity.PurchaseReceipt;
import org.example.springbooterp.mapper.PurchaseReceiptMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-receipt")
public class PurchaseReceiptController {

    @Resource
    private PurchaseReceiptMapper purchaseReceiptMapper;

    @GetMapping
    public List<PurchaseReceipt> list() {
        return purchaseReceiptMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public PurchaseReceipt getById(@PathVariable Long id) {
        return purchaseReceiptMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody PurchaseReceipt purchaseReceipt) {
        return purchaseReceiptMapper.insert(purchaseReceipt) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody PurchaseReceipt purchaseReceipt) {
        purchaseReceipt.setId(id);
        return purchaseReceiptMapper.updateById(purchaseReceipt) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return purchaseReceiptMapper.deleteById(id) > 0;
    }
}
