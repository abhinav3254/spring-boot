package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.repo.UserRespository;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRespository.class)
public class ThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreeApplication.class, args);
	}

}
