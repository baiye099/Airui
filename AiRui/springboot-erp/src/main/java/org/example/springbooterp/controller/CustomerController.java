package org.example.springbooterp.controller;

import org.example.springbooterp.entity.Customer;
import org.example.springbooterp.mapper.CustomerMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Resource
    private CustomerMapper customerMapper;

    @GetMapping
    public List<Customer> list() {
        return customerMapper.selectList(null);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerMapper.selectById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Customer customer) {
        return customerMapper.insert(customer) > 0;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerMapper.updateById(customer) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return customerMapper.deleteById(id) > 0;
    }
}
