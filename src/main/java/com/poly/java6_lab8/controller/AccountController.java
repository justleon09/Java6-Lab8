package com.poly.java6_lab8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
	@RequestMapping(value = "/security/login/form", method = RequestMethod.GET)
	public String loginForm() {
		return "account/login";
	}
}
