package com.poly.java6_lab8.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.poly.java6_lab8.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.poly.java6_lab8.model.Roles;
import com.poly.java6_lab8.model.Account;

public class CustomUserDetails implements UserDetails, OAuth2User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account user;
	private Map<String, Object> attributes;
    
    public CustomUserDetails(Account user) {
        this.user = user;
    }
	
	public CustomUserDetails(Account user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
         
        return authorities;
    }
    
    public Account getUser() {
    	return user;
    }
 
	@Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
	
	@Override
	public String getName() {
		return null;
	}
}
