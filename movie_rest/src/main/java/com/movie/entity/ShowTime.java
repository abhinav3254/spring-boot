package com.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShowTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String movieId;
	private String startTime;
	private String endTime;
	private String theater;
	private String seats;
	public ShowTime() {}
	public ShowTime(long id, String movieId, String startTime, String endTime, String theater, String seats) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.theater = theater;
		this.seats = seats;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTheater() {
		return theater;
	}
	public void setTheater(String theater) {
		this.theater = theater;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "ShowTime [id=" + id + ", movieId=" + movieId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", theater=" + theater + ", seats=" + seats + "]";
	}
}
