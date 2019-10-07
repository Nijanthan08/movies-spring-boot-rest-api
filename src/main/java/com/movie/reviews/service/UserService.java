package com.movie.reviews.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.movie.reviews.domain.User;
import com.movie.reviews.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

@Service
public class UserService {

	@Autowired
	private Environment env;

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public String signUp(User user) {
		String status = "Existing User... Please Login !!!";
		List<User> list = userRepository.fetchUserByEmailId(user.getEmailId());

		if (list.isEmpty()) {
			user.setPassword(hashPassword(user.getPassword()));
			user.setAdmin("N");
			user.setActive("Y");
			user.setCreatedTimestamp(new Date());
			userRepository.save(user);
			status = "Success";
		} else
			LOG.info(status);

		return status;
	}

	private String hashPassword(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
	}

	public User login(User loginUser) {
		List<User> list = userRepository.fetchUserByEmailId(loginUser.getEmailId());
		boolean loginSuccess = false;
		if (!list.isEmpty()) {
			User user = list.get(0);
			loginSuccess = validatePassword(loginUser, user);
			LOG.info("Login Successful : " + loginSuccess);

		}
		return loginSuccess ? list.get(0) : null;

	}

	private boolean validatePassword(User loginUser, User user) {
		Result result = BCrypt.verifyer().verify(loginUser.getPassword().toCharArray(), user.getPassword());
		return result.verified;
	}

	public String buildJsonWebToken(User user) {

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
