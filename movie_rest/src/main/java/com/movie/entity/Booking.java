package com.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String userId;
	private String showTime;
	private String seats;
	private String paymentId;
	private String showId;
	public Booking() {}
	public Booking(long id, String userId, String showTime, String seats, String paymentId, String showId) {
		super();
		this.id = id;
		this.userId = userId;
		this.showTime = showTime;
		this.seats = seats;
		this.paymentId = paymentId;
		this.showId = showId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", showTime=" + showTime + ", seats=" + seats
				+ ", paymentId=" + paymentId + ", showId=" + showId + "]";
	}	
}
