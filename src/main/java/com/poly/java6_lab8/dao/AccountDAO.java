package com.poly.java6_lab8.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.java6_lab8.model.Account;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, String> {
	Optional<Account> findUserByEmail(String email);
}
