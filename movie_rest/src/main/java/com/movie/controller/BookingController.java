package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.movie.entity.Booking;
import com.movie.service.bookingservice.BookingService;

@CrossOrigin(origins = "http://localhost:4200/")
@Controller
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/getBook")
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> list = bookingService.getAllBooking();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
}
