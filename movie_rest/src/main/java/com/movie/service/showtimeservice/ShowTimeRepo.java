package com.movie.service.showtimeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.ShowTime;

public interface ShowTimeRepo extends JpaRepository<ShowTime, Long>{
	
}
