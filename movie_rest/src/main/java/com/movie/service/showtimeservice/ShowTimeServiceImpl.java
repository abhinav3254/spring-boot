package com.movie.service.showtimeservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.ShowTime;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{

	
	@Autowired 
	ShowTimeRepo repo;
	
	@Override
	public List<ShowTime> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<ShowTime> getById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void addShowTime(ShowTime showTime) {
		// TODO Auto-generated method stub
		repo.save(showTime);
	}

	@Override
	public void deleteShowTime(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
