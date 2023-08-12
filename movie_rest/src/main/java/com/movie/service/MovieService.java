package com.movie.service;

import java.util.List;
import java.util.Optional;

import com.movie.entity.Movies;

public interface MovieService {
	public List<Movies> getAll();
	public Optional<Movies> findById(long id);
	public Movies addMovie(Movies movie);
	public Movies updateMovie(Movies movie);
	public void deleteMovie(long id);
}
