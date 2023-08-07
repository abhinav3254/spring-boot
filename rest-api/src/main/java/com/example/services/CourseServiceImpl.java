package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Course;


@Service
public class CourseServiceImpl implements CourseService {
	private List<Course> list;
	@Override
	public List<Course> getAllCourse() {
		list = new ArrayList<>();
		list.add(new Course(1L,"Concept of Physics","$12.99"));
		list.add(new Course(2L,"Unlocking Android","$36.35"));
		list.add(new Course(3L,"Android in Action, Second Edition","$73.21"));
		list.add(new Course(4L,"Flex 3 in Action","$12.99"));
		list.add(new Course(5L,"Collective Intelligence in Action","$18.90"));
		
		return list;
	}

	@Override
	public Course getCourseById(Long id) {
		for (int i = 0;i<list.size();i++) {
			if (list.get(i).getId() == id)
				return list.get(i);
		}
		return null;
	}

}
