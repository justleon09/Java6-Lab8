package com.poly.java6_lab8.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.java6_lab8.model.Product;
import com.poly.java6_lab8.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }
}
