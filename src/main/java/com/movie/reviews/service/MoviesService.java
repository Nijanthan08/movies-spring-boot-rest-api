package com.movie.reviews.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.reviews.controller.MoviesController;
import com.movie.reviews.domain.Genres;
import com.movie.reviews.domain.Languages;
import com.movie.reviews.domain.Movies;
import com.movie.reviews.domain.Ratings;
import com.movie.reviews.domain.Reviews;
import com.movie.reviews.repository.GenreRepository;
import com.movie.reviews.repository.LanguageRepository;
import com.movie.reviews.repository.MoviesRepository;
import com.movie.reviews.repository.RatingsRepository;
import com.movie.reviews.repository.ReviewRepository;

@Service
public class MoviesService {

	private static final Logger LOG = LoggerFactory.getLogger(MoviesService.class);

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private RatingsRepository ratingsRepository;

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

	public void addMovie(Movies movie) {
		movie.setCreatedTimestamp(new Date());
		movie.setLastUpdtTimestamp(new Date());
		movie.setRating(null);
		moviesRepository.save(movie);
	}

	public List<Movies> getMovieInfo(Integer movieId) {

		Optional<Movies> movieOptional = moviesRepository.findById(movieId);
		Movies movie = movieOptional.get();
		// Update Reviews
		List<Reviews> reviews = reviewRepository.fetchReviewByMovieId(movie.getId());
		movie.setReviews(reviews);

		// Update Genre & Language
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

	public void addReview(Reviews reviews) {
		reviews.setCreateTimestamp(new Date());
		reviewRepository.save(reviews);
		Ratings ratings = null;
		List<Ratings> ratingList = ratingsRepository.fetchRatingByMovieId(reviews.getMovieId());
		if (!ratingList.isEmpty()) {
			LOG.info("Update existing ratings...");
			ratings = ratingList.get(0);
		} else {
			LOG.info("Add a new ratings...");
			ratings = new Ratings(reviews.getMovieId(), 0, 0, (double) 0, 0, new Date());
		}

		if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
			ratings.setLikes(ratings.getLikes() + 1);
		} else {
			ratings.setDislike(ratings.getDislike() + 1);
		}
		ratings.setTotalRatings(ratings.getTotalRatings() + 1);
		ratingsRepository.save(ratings);
	}

}
