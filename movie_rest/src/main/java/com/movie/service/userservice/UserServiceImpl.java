package com.movie.service.userservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public boolean addUser(User user) {
		Object ans = userRepo.save(user);
		if (ans  == null) return false;
		else return true;
	}

	@Override
	public void updateUser(User user) {
		System.out.println("Nothing to do");
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		
	}
	
	@Override
	public User findUser(String username) {
		User pass = userRepo.getUser(username);
		System.out.println(pass);
		return pass;
	}
	
}
