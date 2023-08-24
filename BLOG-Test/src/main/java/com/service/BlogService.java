package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Blog;

public interface BlogService {
	
	ResponseEntity<String> addBlog(Map<String, String> requestMap);
	
	ResponseEntity<List<Blog>> getAllBlog();
	
	ResponseEntity<Blog> getBlogById(String id);
	
	ResponseEntity<String> deleteById(String id);
	
}
