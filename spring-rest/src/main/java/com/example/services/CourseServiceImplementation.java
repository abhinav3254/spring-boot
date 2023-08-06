package com.example.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CourseDao;
import com.example.entity.Course;

// This that this class work on service layer
@Service
public class CourseServiceImplementation implements CourseService {
	
	@Autowired
	private CourseDao courseDao;

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
		// TODO Auto-generated method stub
		return courseDao.getOne(courseId);
	}

	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long id) {
		// TODO Auto-generated method stub
		Course course = courseDao.getOne(id);
		courseDao.delete(course);
		
	}
	

	/*
	
	private List<Course> list;
	
	public CourseServiceImplementation() {
		super();
		list = new ArrayList<>();
		list.add(new Course(1,"Unlocking Android",1933988673,416,"Unlocking Android: A Developer's Guide provides concise, hands-on instruction for the Android operating system and development tools. This book teaches important architectural concepts in a straightforward writing style and builds on this with practical and useful examples throughout.","PUBLISH"));
		list.add(new Course(2,"Android in Action, Second Edition",1935182722,592,"This is second book","PUBLISH"));
		
	}

	@Override
	public List<Course> getCourses() {
		return list;
	}
	
	@Override
	public Course getCourse(long courseId) {
		Course course = null;
		for (Course c:list) {
			if (c.getId() == courseId) {
				course = c;
				break;
			}
		}
		return course;
	}

	@Override
	public Course addCourse(Course course) {
		list.add(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		list.forEach(e->{
			if (e.getId() == course.getId()) {
				e.setTitle(course.getTitle());
				e.setIsbn(course.getIsbn());
				e.setPageCount(course.getIsbn());
				e.setStatus(course.getStatus());
				e.setShortDescription(course.getShortDescription());
			}
		});
		return course;
	}

	@Override
	public void deleteCourse(long id) {
		// TODO Auto-generated method stub
		list = this.list.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
		
	}
	*/
}
