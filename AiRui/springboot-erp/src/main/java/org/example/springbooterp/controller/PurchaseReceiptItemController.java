package org.example.springbooterp.controller;

import org.example.springbooterp.entity.PurchaseReceiptItem;
import org.example.springbooterp.mapper.PurchaseReceiptItemMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-receipt-item")
public class PurchaseReceiptItemController {

    @Resource
    private PurchaseReceiptItemMapper purchaseReceiptItemMapper;

    @GetMapping
    public List<PurchaseReceiptItem> list() {
        return purchaseReceiptItemMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public PurchaseReceiptItem getById(@PathVariable Long id) {
        return purchaseReceiptItemMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody PurchaseReceiptItem purchaseReceiptItem) {
        return purchaseReceiptItemMapper.insert(purchaseReceiptItem) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody PurchaseReceiptItem purchaseReceiptItem) {
        purchaseReceiptItem.setId(id);
        return purchaseReceiptItemMapper.updateById(purchaseReceiptItem) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return purchaseReceiptItemMapper.deleteById(id) > 0;
    }
}
