package com.movie.reviews.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.movie.reviews.domain.UserEntity;

public class LoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
		
	public LoginAuthenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	private UserDetailsServiceImpl userDetailsServiceImpl;

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		LOG.info("Retrieve User...");

		UserEntity userEntity = userDetailsServiceImpl.fetchUserByEmailId(username);
		if (null == userEntity)
			throw new AuthenticationServiceException("Username doesn't exist...");

		boolean passwordValid = userDetailsServiceImpl.validatePassword(authentication, userEntity);
		if (!passwordValid)
			throw new AuthenticationServiceException("Invalid Password...");

		return new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
				userEntity.getEmailId(), userEntity.getAdmin(), userEntity.getActive());
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		LOG.info("Additional Authentication Checks...");

	}

	public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

}
