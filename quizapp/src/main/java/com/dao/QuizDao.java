package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
