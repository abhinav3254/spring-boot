package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "<h1>This is Home Page</h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>This is Admin Page</h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>This is User Page</h1>";
	}
	
}
