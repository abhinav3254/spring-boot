package com.movie.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.entity.User;
import com.movie.service.userservice.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@Controller
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getAll")
	public List<User> getAll() {
		return userService.getAllUser();
	}
	
	@GetMapping("/getAll/{id}")
	public Optional<User> getById(@PathVariable String id) {
		return userService.getUserById(Long.parseLong(id));
	}
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody User user) {
		System.out.println(user);
		boolean ans = userService.addUser(user);
		System.out.println(ans);
	} 
	
	
	@DeleteMapping("/delete")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(Long.parseLong(id));
	}
	
	// User LogIn
	
	@PostMapping("/login")
	public ResponseEntity<String> logInUser(@RequestBody Map<String, String> map) {
		User u = userService.findUser(map.get("username"));
		
		if (Objects.isNull(u)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
		} else {
			System.out.println("*******************");
			System.out.println(map.get("username")+"   is th eusername   and password is :--- "+map.get("password"));
			System.out.println(u.getUsername()+"       "+u.getPassword());
			System.out.println("*******************");
			if(map.get("password").equals(u.getPassword()) && map.get("username").equals(u.getUsername())) {
				return ResponseEntity.status(HttpStatus.OK).body("Welcome "+u.getName());
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Password");
			}
		}
	}
	
	// User signUp
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUpUser(@RequestBody User user) {
		User u = userService.findUser(user.getUsername());
		
		if (Objects.isNull(u)) {
			// yes you can signin bcz your username is unique
			userService.addUser(user);
			return ResponseEntity.status(HttpStatus.OK).body("User added");
		} else {
			System.out.println("user already exists");
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
		
	}
	
	
}
