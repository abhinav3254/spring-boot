package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

}
