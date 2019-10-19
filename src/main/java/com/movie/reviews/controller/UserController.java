package com.movie.reviews.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.reviews.domain.UserEntity;
import com.movie.reviews.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody UserEntity loginUser, HttpServletResponse response) {
		String jwt = null;
		
		return "Success";
	}

	@RequestMapping(method = RequestMethod.POST)
	public void signUp(@RequestBody UserEntity user, HttpServletResponse response) {
		LOG.info("Sign Up....");

		try {
			String status = userService.signUp(user);

			if (!"Success".equalsIgnoreCase(status))
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, status);
			else
				response.sendError(HttpServletResponse.SC_OK, status);
		} catch (Exception e) {
			LOG.error("Exception : " + e);
		}
	}

}
