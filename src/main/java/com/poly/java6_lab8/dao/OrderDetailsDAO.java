package com.poly.java6_lab8.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.java6_lab8.model.OrderDetails;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails, Long> {

}
