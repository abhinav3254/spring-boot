package com.movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.entity.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {
	
}
