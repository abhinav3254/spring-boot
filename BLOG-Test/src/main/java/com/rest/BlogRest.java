package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Blog;


@RequestMapping("/blog")
public interface BlogRest {

	@PostMapping("/add")
	ResponseEntity<String> addBlog(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping("/getAll")
	ResponseEntity<List<Blog>> getAllBlog();
	
	@PostMapping("/getbyid/{id}")
	ResponseEntity<Blog> getBlogById(@PathVariable(required = true)String id);
	
	@PostMapping("/delete/{id}")
	ResponseEntity<String> deleteById(@PathVariable(required = true)String id);
	
}
