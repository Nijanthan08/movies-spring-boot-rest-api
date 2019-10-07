package com.movie.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.movie.reviews.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("Select user from User user where user.emailId = :emailId and user.active='Y'")
	List<User> fetchUserByEmailId(@Param("emailId") String emailId);
}
