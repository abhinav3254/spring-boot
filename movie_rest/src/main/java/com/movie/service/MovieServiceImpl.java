package com.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movies;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public List<Movies> getAll() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movies> findById(long id) {
		return movieRepository.findById(id);
	}

	@Override
	public Movies addMovie(Movies movie) {
		movieRepository.save(movie);
		return movie;
	}

	@Override
	public void deleteMovie(long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public Movies updateMovie(Movies movie) {
		return null;
	}
	
}
