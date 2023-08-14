package com.one.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// set my config 
		// First tell which kind of auth you want
		// Here we are doing inMemoryAuthentication
		auth.inMemoryAuthentication()
		.withUser("abhinav")
		.password(passwordEncoder().encode("1234"))
		.roles("ADMIN")
		.and()
		.withUser("aman")
		.password("aman")
		.roles("user");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// Highest Restricted
		.antMatchers("/admin").hasRole("ADMIN")
		// 2nd Highest Restricted
		.antMatchers("/user").hasAnyRole("user","ADMIN")
		// least Restricted
		.antMatchers("/").permitAll()
		.and().formLogin();
	}	
	
	
	
}
