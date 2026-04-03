package org.example.springbooterp.controller;

import org.example.springbooterp.entity.Supplier;
import org.example.springbooterp.mapper.SupplierMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Resource
    private SupplierMapper supplierMapper;

    @GetMapping
    public List<Supplier> list() {
        return supplierMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Supplier supplier) {
        return supplierMapper.insert(supplier) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return supplierMapper.updateById(supplier) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return supplierMapper.deleteById(id) > 0;
    }
}
