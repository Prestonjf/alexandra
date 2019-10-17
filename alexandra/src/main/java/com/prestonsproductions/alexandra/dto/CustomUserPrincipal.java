package com.prestonsproductions.alexandra.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prestonsproductions.alexandra.model.User;

public class CustomUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	private boolean enabled;
	private boolean accountExpired;
	private boolean credentialsExpired;
	private boolean accountLocked;
	private Collection<? extends GrantedAuthority> authorities;
    
    public CustomUserPrincipal(User user, Collection<? extends GrantedAuthority> authorities, 
    		boolean enabled, boolean accountExpired, boolean credentialsExpired, boolean accountLocked) {
        this.user = user;
        this.authorities = authorities;
        this.enabled = enabled;
        this.accountExpired = accountExpired;
        this.credentialsExpired = credentialsExpired;
        this.accountLocked = accountLocked;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}
