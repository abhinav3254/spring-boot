package com.one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
	public String getHello() {
		return "Hello Server";
	}
	
	@GetMapping("/name")
	public String getName() {
		return "<h1>This is ABHINAV KUMAR</h1>";
	}
}
