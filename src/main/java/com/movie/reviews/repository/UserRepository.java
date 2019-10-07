package com.movie.reviews.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.reviews.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
