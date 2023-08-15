package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entites.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUserName(String username);
}
