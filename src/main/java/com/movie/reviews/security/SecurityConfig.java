package com.movie.reviews.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.movie.reviews.domain.User;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

	@Value("${auth-token-header}")
	private String authTokenHeader;

	@Autowired
	private ManageJsonWebTokens jwt;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		AuthTokenHeaderFilter authTokenHeaderFilter = new AuthTokenHeaderFilter(authTokenHeader);

		authTokenHeaderFilter.setAuthenticationManager(new AuthenticationManager() {

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String authenticationToken = (String) authentication.getPrincipal();
				User user = jwt.decode(authenticationToken);

				if (null == user) {
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});

		http.antMatcher("/api/**").csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(authTokenHeaderFilter)
				.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()),
						authTokenHeaderFilter.getClass())
				.authorizeRequests().anyRequest().authenticated();
	}

}
