package com.poly.java6_lab8.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.java6_lab8.model.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

}
