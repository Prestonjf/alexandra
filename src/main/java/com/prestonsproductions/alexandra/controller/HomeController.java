package com.prestonsproductions.alexandra.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prestonsproductions.alexandra.service.ScienceOlympiadService;
import com.typesafe.config.Config;

/**
 * @author Preston Frazier
 *
 */
@Controller
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ScienceOlympiadService springTemplateService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private Config properties;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		addCommonModelItems(model);
		model.setViewName("login");	
		model.addObject("msg", request.getSession().getAttribute("loginErrorMessage")); request.getSession().removeAttribute("loginErrorMessage");
		
        return model;       
    }
    
    @RequestMapping(value = "/auth-login", method = RequestMethod.POST)
    public ModelAndView loginAuth() {
    	ModelAndView model = new ModelAndView();
    	addCommonModelItems(model);
    	model.setViewName("login");
    	if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
    		return new ModelAndView("redirect:/home");
    	}
    	else {
    		
    	}
    	return model;  
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView home() {
    	logger.debug("Loading Home Page!"); 
		ModelAndView model = new ModelAndView();
		addCommonModelItems(model);
		model.addObject("msg", "Welcome Home to Alexandra!");
		model.setViewName("home");
        return model;       
    }

    @RequestMapping(value = "/error-page", method = RequestMethod.GET)
    public ModelAndView errorpage(){
		ModelAndView model = new ModelAndView();
		addCommonModelItems(model);
		model.addObject("msg", "Not Authenticated!");
		model.setViewName("error-page");
        return model;
    }
    
    private void addCommonModelItems(ModelAndView model) {
    	model.addObject("properties", properties);
    }
    
}
