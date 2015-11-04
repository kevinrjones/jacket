package com.pluralsight.controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
	Log log;
	
	@Inject
	public Login(Log log) {
		log.info("*********Login ctor");
		this.log = log;
	}

	@RequestMapping(value = "/login")
	public String login() {
		log.info("*********Login method");
		return "login/login";
	}

}
