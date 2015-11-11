package com.pluralsight.controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pluralsight.jacket.models.AuthenticatedUser;
import com.pluralsight.jacket.models.JacketUser;
import com.pluralsight.jacket.services.EmailExistsException;
import com.pluralsight.jacket.services.JacketUserService;

@Controller
public class AuthenticationController {
	Log log;
	JacketUserService jacketUserService;
	
	
	@Inject
	public AuthenticationController(Log log, JacketUserService jacketUserService) {
		log.debug("*********Login ctor");
		this.log = log;
		this.jacketUserService = jacketUserService;
	}

	@RequestMapping(value = "/login")
	public String login() {
		log.debug("*********Login method");
		return "authenticate/login";
	}

	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String register() {
		return "authenticate/register";
	}
	
	// todo: use a VM instead of JacketUser
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(JacketUser user) {
		try {
			jacketUserService.registerNewUserAccount(user);
		} catch (EmailExistsException e) {
			return register();
		}
		return "redirect:/";
	}

}
