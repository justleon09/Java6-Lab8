package com.poly.java6_lab8.security.oauth2;

import com.poly.java6_lab8.dao.AccountDAO;
import com.poly.java6_lab8.model.Account;
import com.poly.java6_lab8.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	AccountDAO userRepo;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	    OAuth2User oAuth2User = super.loadUser(userRequest);
    	Account user = userRepo.findUserByEmail(oAuth2User.getAttribute("email")).get();
    	if(user == null) {
    		throw new UsernameNotFoundException("Could not find user");
    	}
    	
    	return new CustomUserDetails(user, oAuth2User.getAttributes());
	}
	
}
