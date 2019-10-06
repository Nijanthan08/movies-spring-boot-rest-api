package com.movie.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.movie.reviews.domain.Reviews;

public interface ReviewRepository extends CrudRepository<Reviews, Integer> {
	
	@Query("Select reviews from Reviews reviews where reviews.movieId= :movieId")
	List<Reviews> fetchReviewByMovieId(@Param("movieId") Integer movieId);

}
