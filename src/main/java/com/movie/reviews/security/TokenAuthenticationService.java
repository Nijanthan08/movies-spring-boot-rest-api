package com.movie.reviews.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.movie.reviews.domain.Role;

@Service
public class TokenAuthenticationService {

	private static final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationService.class);

	@Autowired
	private Environment env;

	public String build(User user) {
		LOG.info("Build Json Web Token...");
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(env.getProperty("privateKey"));
			token = JWT.create().withClaim("id", user.getId()).withClaim("firstName", user.getFirstName())
					.withClaim("lastName", user.getLastName()).withClaim("emailId", user.getEmailId())
					.withClaim("admin", user.getAdmin()).withClaim("active", user.getActive()).sign(algorithm);
		} catch (Exception e) {
			LOG.error("Exception: " + e);
		}
		return token;

	}

	public User decode(String jwt) {
		LOG.info("Decode Json Web Token...");
		User user = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(env.getProperty("privateKey"));
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwtDecoded = verifier.verify(jwt);

			String admin = jwtDecoded.getClaim("admin").asString();

			user = new User(jwtDecoded.getClaim("id").asInt(), jwtDecoded.getClaim("firstName").asString(),
					jwtDecoded.getClaim("lastName").asString(), jwtDecoded.getClaim("emailId").asString(), admin,
					jwtDecoded.getClaim("active").asString());
		} catch (Exception exception) {
			LOG.error("Exception: " + exception);
		}
		return user;

	}

}
