package com.poly.java6_lab8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.java6_lab8.model.Category;
import com.poly.java6_lab8.model.Product;
import com.poly.java6_lab8.service.CategoryService;
import com.poly.java6_lab8.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product/list")
    public String list(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("items", list);
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = productService.findById(id);
        model.addAttribute("item", item);
        return "product/detail";
    }
}
