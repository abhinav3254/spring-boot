package com.movie.service.bookingservice;

import java.util.List;
import java.util.Optional;

import com.movie.entity.Booking;

public interface BookingService {
	public List<Booking> getAllBooking();
	public Optional<Booking> getBookingById(long id);
	public void addBooking(Booking booking);
	public void deleteBooking(long id);
}
