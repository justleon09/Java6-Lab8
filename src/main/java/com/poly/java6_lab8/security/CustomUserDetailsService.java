package com.poly.java6_lab8.security;


import com.poly.java6_lab8.dao.AccountDAO;
import com.poly.java6_lab8.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	AccountDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Account user = userRepository.findById(username).get();

    	if(user == null) {
    		throw new UsernameNotFoundException("Could not find user");
    	}
    	
    	return new CustomUserDetails(user);
    }
}