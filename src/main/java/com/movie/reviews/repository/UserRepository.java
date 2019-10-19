package com.movie.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.movie.reviews.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	@Query("Select user from UserEntity user where user.emailId = :emailId and user.active='Y'")
	List<UserEntity> fetchUserByEmailId(@Param("emailId") String emailId);
}
