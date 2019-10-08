package com.movie.reviews.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.movie.reviews.domain.User;

@Service
public class ManageJsonWebTokens {
	

	private static final Logger LOG = LoggerFactory.getLogger(ManageJsonWebTokens.class);
	
	@Autowired
	private Environment env;
	
	public String build(User user) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(env.getProperty("privateKey"));
			String token = JWT.create().withClaim("id", user.getId()).withClaim("firstName", user.getFirstName())
					.withClaim("lastName", user.getLastName()).withClaim("emailId", user.getEmailId())
					.withClaim("admin", user.getAdmin()).sign(algorithm);
			LOG.debug(token);
			return token;
		} catch (Exception e) {
			LOG.error("Exception: " + e);
		}
		return null;
	}

}
