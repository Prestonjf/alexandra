package com.prestonsproductions.alexandra.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.typesafe.config.Config;

/**
 * @author Preston Frazier
 *
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private Config properties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);		
	}
	
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {	
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		request.getSession().setMaxInactiveInterval(60*Integer.parseInt(properties.getString("com.prestonsproductions.alexandra.SESSION_TIMEOUT")));
		String targetUrl = "/home";
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}


}
