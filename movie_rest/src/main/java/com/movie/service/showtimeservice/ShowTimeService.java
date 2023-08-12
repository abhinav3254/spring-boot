package com.movie.service.showtimeservice;

import java.util.List;
import java.util.Optional;

import com.movie.entity.ShowTime;

public interface ShowTimeService {
	public List<ShowTime> getAll();
	public Optional<ShowTime> getById(long id);
	public void addShowTime(ShowTime showTime);
	public void deleteShowTime(long id);
}
