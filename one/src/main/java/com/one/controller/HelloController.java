package com.one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
	public String getHello() {
		return "<h1>Hello This is Public Page From the Server</h1>";
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "<h1>Hello User From the Server</h1>";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "<h1>Hello Admin From the Server</h1>";
	}
	
}
