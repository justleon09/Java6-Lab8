package com.poly.java6_lab8.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.java6_lab8.dao.OrderDAO;
import com.poly.java6_lab8.dao.OrderDetailsDAO;
import com.poly.java6_lab8.model.Order;
import com.poly.java6_lab8.model.OrderDetails;

@Service
public class OrderService {
    @Autowired
    OrderDAO dao;

    @Autowired
    OrderDetailsDAO ddao;

    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData, Order.class);
        dao.save(order);

        TypeReference<List<OrderDetails>> type = new TypeReference<List<OrderDetails>>() {
        };
        List<OrderDetails> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        ddao.saveAll(details);

        return order;
    }

    public Order findById(Long id) {
        return dao.findById(id).get();
    }

    public List<Order> findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
