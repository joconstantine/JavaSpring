package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.login.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String sayHello() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLoginRequest(
			@RequestParam String name,
			@RequestParam(name = "password") String inputPassword,
			ModelMap model) {
		
		if (!service.isUserValid(name, inputPassword))
		{
			model.put("errorMessage", "Invalid Credential!");
			return "login";
		}
		
		model.put("name", name);
		model.put("password", inputPassword);
		return "welcome";
	}
}
