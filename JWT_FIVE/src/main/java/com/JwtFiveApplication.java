package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtFiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtFiveApplication.class, args);
	}

}

/*
 * Change the data source from the user details service 
 * MyUserDetailsService  this class in com.service package
 */
