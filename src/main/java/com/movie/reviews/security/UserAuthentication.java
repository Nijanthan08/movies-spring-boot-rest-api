package com.movie.reviews.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthentication(User user) {
		super();
		this.user = user;
	}

	private User user;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getFirstName() + user.getLastName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getEmailId();
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

}
