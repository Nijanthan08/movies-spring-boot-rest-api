package com.movie.reviews.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.reviews.domain.UserEntity;
import com.movie.reviews.repository.UserRepository;
import com.movie.reviews.security.TokenAuthenticationService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public String signUp(UserEntity user) {
		String status = "Existing User... Please Login !!!";
		List<UserEntity> list = userRepository.fetchUserByEmailId(user.getEmailId());

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

}
