package com.prestonsproductions.alexandra.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.typesafe.config.Config;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {
               
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Config properties;
   
    /**
    * Executed before actual handler is executed
    **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
	    logger.debug("Pre handle method - check execution time of handling");
	    if (properties == null) {
	    	WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    	properties = webApplicationContext.getBean(Config.class);
	    }
	    setSessionTimeoutCookie(request, response);
	    return true;
	}
 
                /**
    * Executed before after handler is executed
    **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                                    final ModelAndView model) throws Exception {
    	logger.debug("Post handle method - check execution time of handling");
    }
               
	private void setSessionTimeoutCookie(HttpServletRequest request, HttpServletResponse response) {
        long currTime = System.currentTimeMillis();
        long expiryTime = currTime + request.getSession().getMaxInactiveInterval() * 1000;
        if (request.getSession().getMaxInactiveInterval() < 0) {
               currTime = -1;
               expiryTime = -1;
        }
        Cookie cookie = new Cookie("serverTime", "" + currTime);
        cookie.setPath(properties.getString("com.prestonsproductions.alexandra.BASE_URL"));
        response.addCookie(cookie);
        if (request.getRemoteUser() != null) {
        	cookie = new Cookie("sessionExpiry", "" + expiryTime);
        } else {
        	cookie = new Cookie("sessionExpiry", "" + currTime);
        }
        cookie.setPath(properties.getString("com.prestonsproductions.alexandra.BASE_URL"));
        response.addCookie(cookie);
	}

}
