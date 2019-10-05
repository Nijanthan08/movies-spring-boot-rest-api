package com.movie.reviews.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews {

	@Id
	@Column(name = "ID")
	private Integer reviewId;

	@Column(name = "movieId")
	private Integer movie;

	@Column(name = "createdUserId")
	private Integer createdUserId;

	@Column(name = "createdUserName")
	private String createdUserName;

	@Column(name = "likeMovie")
	private String likeMovie;

	@Column(name = "comments")
	private String comments;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "createTimestamp")
	private String createTimestamp;

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getMovie() {
		return movie;
	}

	public void setMovie(Integer movie) {
		this.movie = movie;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public String getLikeMovie() {
		return likeMovie;
	}

	public void setLikeMovie(String likeMovie) {
		this.likeMovie = likeMovie;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
}
