package com.movie.reviews.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class AuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

	private String authHeaderName;

	public AuthTokenHeaderFilter(String authHeaderName) {
		this.authHeaderName = authHeaderName;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return request.getHeader(authHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}
}
