package com.prestonsproductions.alexandra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prestonsproductions.alexandra.dao.UserDAO;
import com.prestonsproductions.alexandra.dto.CustomUserPrincipal;
import com.prestonsproductions.alexandra.model.User;

@Service
public class CustomUserDetailService implements UserDetailsService {
   
	@Autowired
    private UserDAO userDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user =  userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserPrincipal(user);
    }
}
