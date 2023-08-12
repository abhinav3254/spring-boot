package com.movie.service.userservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movie.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	/*
	@Query(value = "select u.password from user u where u.username =:username")
	Optional<String> getPassword(String username);
	*/
	
	User getUser(@Param("username") String username);
}
