package com.movie.service.userservice;

import java.util.List;
import java.util.Optional;

import com.movie.entity.User;

public interface UserService {
	public List<User> getAllUser();
	public Optional<User> getUserById(Long id);
	public boolean addUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long id);
	public User findUser(String username);
}
