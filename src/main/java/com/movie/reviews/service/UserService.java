package com.movie.reviews.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.reviews.domain.User;
import com.movie.reviews.repository.UserRepository;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public String signUp(User user) {
		String status = "Existing User... Please Login !!!";
		List<User> list = userRepository.fetchUserByEmailId(user.getEmailId());
		
		if (list.isEmpty()) {
			user.setAdmin("N");
			user.setActive("Y");
			user.setCreatedTimestamp(new Date());
			userRepository.save(user);
			status = "Done";
		} else
			LOG.info(status);
		
		return status;
	}

}
