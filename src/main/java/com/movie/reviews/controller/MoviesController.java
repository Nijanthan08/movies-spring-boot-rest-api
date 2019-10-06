package com.movie.reviews.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.reviews.domain.Genres;
import com.movie.reviews.domain.Languages;
import com.movie.reviews.domain.Movies;
import com.movie.reviews.domain.Reviews;
import com.movie.reviews.service.MoviesService;


@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MoviesController {

	private static final Logger LOG = LoggerFactory.getLogger(MoviesController.class);

	@Autowired
	private MoviesService moviesService;

	@RequestMapping(value = "/languages", method = RequestMethod.GET)
	public List<Languages> getAllLanguages() {
		return moviesService.getAllLanguages();
	}

	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public List<Genres> getAllGenres() {
		return moviesService.getAllGenres();
	}

	@RequestMapping(value = "/popular", method = RequestMethod.GET)
	public List<Movies> getPopularMovies() {
		LOG.info("Fetch Popular Movies...");
		List<Movies> list = moviesService.getAllMovies();
		LOG.debug(": " + list.size());
		List<Movies> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
				.collect(Collectors.toList());
		LOG.debug(": " + ratedMovies.size());
		return ratedMovies;
	}

	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public List<Movies> getMovieInfo(@PathVariable("movieId") Integer movieId) {
		return moviesService.getMovieInfo(movieId);
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public List<Movies> addMovieReview(@RequestBody Reviews reviews) {
		LOG.info("Add Movie Reviews...");
		moviesService.addReview(reviews);
		
		return moviesService.getMovieInfo(reviews.getMovieId());
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Movies> getAllMovies() {
		LOG.info("Fetch all the Movies...");
		return moviesService.getAllMovies();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public List<Movies> addMovie(@RequestBody Movies movie) {
		LOG.info("Add a Movie...");
		moviesService.addMovie(movie);
		return moviesService.getAllMovies();
	}

}
