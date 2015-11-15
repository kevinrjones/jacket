package com.pluralsight.jacketweb.controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pluralsight.jacket.security.service.models.AuthenticatedUser;
import com.pluralsight.jacket.services.JacketEntryService;
import com.pluralsight.security.CurrentUser;

@Controller
@RequestMapping(value = { "/", "/link" })
public class LinkController {

	private JacketEntryService service;
	private Log log;

	@Inject
	public LinkController(JacketEntryService service, Log log) {
		this.service = service;
		this.log = log;
	}

	@RequestMapping(value = {"/", ""})
	public ModelAndView index(@CurrentUser AuthenticatedUser user) {
		service.getAllEntries();
		ModelAndView mv = new ModelAndView("link/index");
		// map authenticateduser to VM
		mv.addObject("user", user);		
		return mv;
	}
}
