package com.restimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.Blog;
import com.rest.BlogRest;
import com.service.BlogService;

@RestController
public class BlogRestImpl implements BlogRest {

	@Autowired
	private BlogService blogService;
	
	@Override
	public ResponseEntity<String> addBlog(Map<String, String> requestMap) {
		try {
			return blogService.addBlog(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Internal Issue in BlogRestImpl",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Blog>> getAllBlog() {
		try {
			return blogService.getAllBlog();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Blog>>(new ArrayList<Blog>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Blog> getBlogById(String id) {
		try {
			return blogService.getBlogById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Blog>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteById(String id) {
		try {
			return blogService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
