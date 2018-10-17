package com.prestonsproductions.alexandra.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.prestonsproductions.alexandra.dao.UserDAO;
import com.prestonsproductions.alexandra.dto.CustomUserPrincipal;
import com.prestonsproductions.alexandra.model.Role;
import com.prestonsproductions.alexandra.model.User;

/**
 * @author Preston Frazier
 *
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String name = auth.getPrincipal().toString();
		String password = auth.getCredentials().toString();
		User user = userDAO.findByUsername(name);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (user != null && name.equalsIgnoreCase(user.getUsername()) && encoder.matches(password, user.getPassword())) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			for (Role r : user.getRoles()) {
				grantedAuths.add(new SimpleGrantedAuthority(r.getName()));
			}
			CustomUserPrincipal cup = new CustomUserPrincipal(user, grantedAuths, true, true, true, true);
			UsernamePasswordAuthenticationToken authenticiation = new UsernamePasswordAuthenticationToken(cup, password, grantedAuths);
			return authenticiation;
		} else {
			throw new BadCredentialsException("Bad User Credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
