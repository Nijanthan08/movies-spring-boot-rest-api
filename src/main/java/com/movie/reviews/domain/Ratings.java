package com.movie.reviews.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Ratings {

	public Ratings() {
	}

	public Ratings(Integer movieId, Integer likes, Integer dislike, Double rating, Integer totalRatings,
			Date createTimestamp) {
		super();
		this.movieId = movieId;
		this.likes = likes;
		this.dislike = dislike;
		this.rating = rating;
		this.totalRatings = totalRatings;
		this.createTimestamp = createTimestamp;
	}

	@Id
	@Column(name = "movieId")
	private Integer movieId;

	@Column(name = "likes")
	private Integer likes;

	@Column(name = "dislike")
	private Integer dislike;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "totalRatings")
	private Integer totalRatings;

	@Column(name = "createTimestamp")
	private Date createTimestamp;

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

	public Integer getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Integer totalRatings) {
		this.totalRatings = totalRatings;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

}
