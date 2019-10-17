package com.prestonsproductions.alexandra.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @GetMapping(value = "/login")
    public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");	
		model.addObject("msg", "");
		addCommonModelItems(model);
        return model;       
    }
    
    
    @GetMapping(value = {"/", "/home"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView home() {
    	logger.debug("Loading Home Page!"); 
		ModelAndView model = new ModelAndView();
		model.addObject("msg", "Welcome Home to Alexandra!");
		model.setViewName("home");	
		addCommonModelItems(model);
        return model;       
    }
    
    @RequestMapping(value = {"/invalid-session"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView invalidSession() {
    	logger.debug("Invalid Session"); 
    	request.getSession().invalidate();
		ModelAndView model = new ModelAndView();
		model.addObject("warningmsg", "Your session has expired!");
		model.setViewName("login");
		addCommonModelItems(model);
        return model;       
    }
    
    @PostMapping(value = "/refreshSession")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody String refreshSession() {
    	logger.debug("Refresh Session");
    	return "{}";
    }
    
    @GetMapping(value = {"/error"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView error() {
    	logger.debug("Error Page"); 
		ModelAndView model = new ModelAndView();
		addCommonModelItems(model);
		model.addObject("msg", "Page Unavailable");
		model.setViewName("error-page");
        return model;       
    }
    
    private void addCommonModelItems(ModelAndView model) {
    	model.addObject("properties", properties);
    }
    
}
