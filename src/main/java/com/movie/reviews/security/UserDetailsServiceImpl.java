package com.movie.reviews.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.reviews.domain.UserEntity;
import com.movie.reviews.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("Load User By Username...");
		User user = null;
		UserEntity userEntity = fetchUserByEmailId(username);
		if (null != userEntity)
			user = new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
					userEntity.getEmailId(), userEntity.getEmailId(), userEntity.getActive());

		return user;
	}

	protected UserEntity fetchUserByEmailId(String username) {
		LOG.info("Fetch User By EmailId...");
		List<UserEntity> list = userRepository.fetchUserByEmailId(username);
		return (!list.isEmpty()) ? list.get(0) : null;
	}

	public boolean validatePassword(UsernamePasswordAuthenticationToken authentication, UserEntity user) {
		LOG.info("Validate Password...");
		Result result = BCrypt.verifyer().verify(authentication.getCredentials().toString().toCharArray(),
				user.getPassword());
		return result.verified;
	}

}
