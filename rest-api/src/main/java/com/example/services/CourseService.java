package com.example.services;

import java.util.List;

import com.example.entity.Course;

public interface CourseService {
	public List<Course> getAllCourse();
	public Course getCourseById(Long id);
}
