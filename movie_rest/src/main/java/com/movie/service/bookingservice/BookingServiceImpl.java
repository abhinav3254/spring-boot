package com.movie.service.bookingservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Booking;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
	@Override
	public List<Booking> getAllBooking() {
		return bookingRepo.findAll();
	}

	@Override
	public Optional<Booking> getBookingById(long id) {
		return bookingRepo.findById(id);
	}

	@Override
	public void addBooking(Booking booking) {
		bookingRepo.save(booking);
		
	}

	@Override
	public void deleteBooking(long id) {
		bookingRepo.deleteById(id);
		
	}
	
}
