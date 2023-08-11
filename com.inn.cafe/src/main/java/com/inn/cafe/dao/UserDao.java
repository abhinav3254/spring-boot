package com.inn.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.inn.cafe.pojo.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	// This method defined inside the User class
	User findByEmailId(@Param("email") String email);
	
}
