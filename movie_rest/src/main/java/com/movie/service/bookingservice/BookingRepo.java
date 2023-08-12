package com.movie.service.bookingservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long>{

}
