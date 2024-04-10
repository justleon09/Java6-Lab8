package com.poly.java6_lab8.config;

import com.poly.java6_lab8.security.CustomUserDetailsService;
import com.poly.java6_lab8.security.oauth2.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import com.poly.java6_lab8.security.CustomUserDetailsService;
import com.poly.java6_lab8.security.oauth2.CustomOAuth2UserService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired
	CustomOAuth2UserService auth2UserService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request ->
						request.requestMatchers("/security/logoff").authenticated()
								.requestMatchers("/admin/**").hasAuthority("ADMIN")
								.anyRequest().permitAll())
				.formLogin(login ->
						login.loginPage("/security/login/form")
								.loginProcessingUrl("/security/login")
								.defaultSuccessUrl("/product/list")
								.failureUrl("/security/login/form"))
//								.successHandler(successHandler)
//								.failureHandler(failureHandler))
				.rememberMe(remember ->
						remember.key("abc21412bf1u1ur2v2yf")
								.tokenValiditySeconds(86400))
				.oauth2Login(login ->
						login.loginPage("/security/login/form")
								.authorizationEndpoint(e -> e.baseUri("/oauth2/authorization"))
								.redirectionEndpoint(e -> e.baseUri("/login/oauth2/code/*"))
								.userInfoEndpoint(e -> e.userService(auth2UserService))
								.defaultSuccessUrl("/product/list")
								.failureUrl("/security/login/form"))
//								.successHandler(successHandler)
//								.failureHandler(failureHandler))
				.logout(logout ->
						logout.logoutUrl("/securiry/logoff")
								.logoutSuccessUrl("/product/list"))
//								.logoutSuccessHandler(logoutSuccessHander))
				.exceptionHandling(e ->
						e.accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/product/list")))
				.authenticationProvider(authenticationProvider());
		return http.build();
	}
}
