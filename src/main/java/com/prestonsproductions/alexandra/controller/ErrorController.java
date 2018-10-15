package com.prestonsproductions.alexandra.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Preston Frazier
 *
 */
@ControllerAdvice
public class ErrorController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
    public ModelAndView errorPage(HttpServletRequest request, Exception e) {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("error-page");

        String errorMsg = "";
        int httpErrorCode = getErrorCode(request);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "400 Bad Request";
                break;
            }
            case 401: {
                errorMsg = "401 Unauthorized";
                break;
            }
            case 403: {
                errorMsg = "403 You do not have permission to access this page";
                break;
            }
            case 404: {
                errorMsg = "404 Page Not Found";
                break;
            }
            case 405: {
                errorMsg = "405 Page Not Found";
                break;
            }
            case 500: {
                errorMsg = "500 Internal Server Error";
                break;
            }
            default: {
                errorMsg = "Page Unavailable";
                break;
            }
        }
        LOGGER.error(errorMsg + " " +getErrorURI(request), e);
        mv.addObject("msg", errorMsg);
        return mv;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
    	if (httpRequest == null || httpRequest.getAttribute("javax.servlet.error.status_code") == null) return 501;
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
    
    private String getErrorURI(HttpServletRequest httpRequest) {
    	if (httpRequest == null || httpRequest.getAttribute("javax.servlet.error.request_uri") == null) return "";
        return httpRequest.getAttribute("javax.servlet.error.request_uri").toString();
    }
}
