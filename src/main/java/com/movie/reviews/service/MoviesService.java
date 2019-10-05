package com.movie.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.reviews.domain.Genres;
import com.movie.reviews.domain.Languages;
import com.movie.reviews.repository.GenreRepository;
import com.movie.reviews.repository.LanguageRepository;

@Service
public class MoviesService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private GenreRepository genreRepository;

	public List<Languages> getAllLanguages() {
		return (List<Languages>) languageRepository.findAll();
	}

	public List<Genres> getAllGenres() {
		return (List<Genres>) genreRepository.findAll();
	}

}
