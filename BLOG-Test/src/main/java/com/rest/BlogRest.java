package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/blog")
public interface BlogRest {

	@PostMapping("/add")
	ResponseEntity<String> addBlog(@RequestBody(required = true) Map<String, String> requestMap);
	
}
