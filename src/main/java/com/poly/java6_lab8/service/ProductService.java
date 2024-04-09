package com.poly.java6_lab8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.java6_lab8.dao.ProductDAO;
import com.poly.java6_lab8.model.Product;

@Service
public class ProductService {
    @Autowired
    ProductDAO dao;

    public List<Product> findAll() {
        return dao.findAll();
    }
}
