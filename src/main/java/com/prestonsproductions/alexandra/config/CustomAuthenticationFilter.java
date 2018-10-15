package com.prestonsproductions.alexandra.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.typesafe.config.Config;

/**
 * @author Preston Frazier
 *
 */
@Component("CustomAuthenticationFilter")
public class CustomAuthenticationFilter extends GenericFilterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Config properties;
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { 
    	HttpServletRequest req = (HttpServletRequest) request;
        	if (properties == null) {
    		ServletContext servletContext = request.getServletContext();
    		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    		properties = webApplicationContext.getBean(Config.class);
    	}
    	
    	if ("/auth-login".equals(req.getServletPath())) {
    		doAuthentication(request);
    		req.getSession().setMaxInactiveInterval(60*60 * 4);
    	}
		   	
    	chain.doFilter(request, response);
    }
    /**
     * Application by default creates an Authorization in memory. An external datasource
     * could be used to get user credentials. Application will login upon /loging-auth url.
     * @param request
     */
    private void doAuthentication(ServletRequest request) {
		logger.debug("AUTHENTICATING USER...");
		HttpServletRequest req = (HttpServletRequest) request;
		String username = "user";
		if (!"error".equalsIgnoreCase(request.getParameter("username")))
				authenticateUser(username, "password");
		else req.getSession().setAttribute("loginErrorMessage", "Username or Password is incorrect.");
    }
    
    private void authenticateUser(String username, String password) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        @SuppressWarnings("serial")
		GrantedAuthority grantedAuthority = new GrantedAuthority() {
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        grantedAuthorities.add(grantedAuthority);	
		Authentication authToken = new UsernamePasswordAuthenticationToken(username, password,grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authToken);
    }


}
