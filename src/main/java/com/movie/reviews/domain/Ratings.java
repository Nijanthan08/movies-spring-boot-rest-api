package com.movie.reviews.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ratings")
public class Ratings {

	@Id
	@Column(name = "id")
	private Integer ratingId;

	@NaturalId
	@Column(name = "movieId")
	private Integer movieId;

	@Column(name = "likes")
	private Integer likes;

	@Column(name = "dislike")
	private Integer dislike;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "totalRatings")
	private Double totalRatings;
	
	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislike() {
		return dislike;
	}

	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Double getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Double totalRatings) {
		this.totalRatings = totalRatings;
	}
	
	
}
