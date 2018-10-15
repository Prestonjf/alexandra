package com.prestonsproductions.alexandra.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author Preston Frazier
 *
 */
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {	
		request.getSession().setMaxInactiveInterval(30 * 60);		
		String targetUrl = "/home";
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}	
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
