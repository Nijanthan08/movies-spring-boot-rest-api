package com.movie.reviews.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.reviews.domain.UserEntity;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		this.authenticationManager = authenticationManager;
	}

	private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

	private AuthenticationManager authenticationManager;

	private UserEntity userEntity;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		LOG.info("Attempt Authentication...");

		ObjectMapper mapper = new ObjectMapper();
		setUserEntity(mapper.readValue(request.getInputStream(), UserEntity.class));

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userEntity.getEmailId(), userEntity.getPassword());
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LOG.info("Successful Authentication...");

		User user = (User) authResult.getDetails();
		SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));

		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		LOG.error("Unsuccessful Authentication for the userName: " + userEntity.getEmailId());

		super.unsuccessfulAuthentication(request, response, failed);
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
