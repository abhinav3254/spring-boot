package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Course;
import com.example.services.CourseService;

@RestController
public class MyController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/")
	public String hello() {
		return "Hello Spring-boot";
	}
	
	@GetMapping("/courses")
	public List<Course> getCourses() {
		return courseService.getAllCourse();
	}
	
	@GetMapping("/courses/{id}")
	public Course getCourseById(@PathVariable String id) {
		return courseService.getCourseById(Long.parseLong(id));
	}
}
