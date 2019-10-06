package com.movie.reviews.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.reviews.domain.Genres;
import com.movie.reviews.domain.Languages;
import com.movie.reviews.domain.Movies;
import com.movie.reviews.domain.Reviews;
import com.movie.reviews.repository.GenreRepository;
import com.movie.reviews.repository.LanguageRepository;
import com.movie.reviews.repository.MoviesRepository;
import com.movie.reviews.repository.ReviewRepository;

@Service
public class MoviesService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	public List<Languages> getAllLanguages() {
		return (List<Languages>) languageRepository.findAll();
	}

	public List<Genres> getAllGenres() {
		return (List<Genres>) genreRepository.findAll();
	}

	public List<Movies> getAllMovies() {
		List<Movies> list = (List<Movies>) moviesRepository.findAll();
		updateLanguageGenres(list);
		return list;
	}

	public List<Movies> getMovieInfo(Integer movieId) {

		Optional<Movies> movieOptional = moviesRepository.findById(movieId);
		Movies movie = movieOptional.get();
		//Update Reviews
		List<Reviews> reviews = reviewRepository.fetchReviewByMovieId(movie.getId());
		movie.setReviews(reviews);
		
		//Update Genre & Language
		List<Movies> list = Arrays.asList(new Movies[] { movie });
		updateLanguageGenres(list);

		return list;
	}

	private void updateLanguageGenres(List<Movies> list) {
		Map<Integer, String> languageMap = new HashMap<>();
		Map<Integer, String> genreMap = new HashMap<>();

		List<Languages> languageList = getAllLanguages();
		languageList.parallelStream().forEach(obj -> languageMap.put(obj.getId(), obj.getName()));

		List<Genres> genreList = getAllGenres();
		genreList.parallelStream().forEach(obj -> genreMap.put(obj.getId(), obj.getName()));

		list.parallelStream().forEach(obj -> {
			obj.setLanguage(languageMap.get(obj.getLanguageId()));
			obj.setGenre(genreMap.get(obj.getGenreId()));
		});

	}

}
