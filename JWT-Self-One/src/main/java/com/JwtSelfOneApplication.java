package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.config.SecurityConfig;

@SpringBootApplication
@ComponentScan(excludeFilters = 
{@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})

public class JwtSelfOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSelfOneApplication.class, args);
	}

}
