package com.movie.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.reviews.domain.Genres;
import com.movie.reviews.domain.Languages;
import com.movie.reviews.domain.Movies;
import com.movie.reviews.service.MoviesService;

@RestController
@RequestMapping("/movies")
public class MoviesController {

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
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Movies> getAllMovies() {
		return moviesService.getAllMovies();
	}

}
