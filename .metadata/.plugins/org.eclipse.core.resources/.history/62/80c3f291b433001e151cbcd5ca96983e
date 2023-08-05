package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FirstProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(FirstProjectApplication.class, args);
		System.out.println("hello spring-boot");
		// Dependecy injection
		Aliean aliean = configurableApplicationContext.getBean(Aliean.class);
		
		System.out.println(aliean.toString());
	}

}
