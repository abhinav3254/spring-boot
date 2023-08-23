package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BlogService {
	
	ResponseEntity<String> addBlog(Map<String, String> requestMap);
	
}
