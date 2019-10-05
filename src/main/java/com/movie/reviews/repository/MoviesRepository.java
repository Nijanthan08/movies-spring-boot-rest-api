package com.movie.reviews.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.reviews.domain.Movies;

public interface MoviesRepository extends CrudRepository<Movies, Integer> {

}
