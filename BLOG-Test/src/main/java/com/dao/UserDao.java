package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pojo.User;


public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query(nativeQuery = true,value = "select * from user where email=:email")
	User findByEmail(@Param("email")String email);
	
}
