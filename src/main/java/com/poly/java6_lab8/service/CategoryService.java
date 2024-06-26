package com.poly.java6_lab8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.java6_lab8.dao.CategoryDAO;
import com.poly.java6_lab8.model.Category;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO dao;

    public List<Category> findAll() {
        return dao.findAll();
    }
}
