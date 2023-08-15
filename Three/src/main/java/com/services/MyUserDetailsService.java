package com.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entites.MyUserDetails;
import com.entites.User;
import com.repo.UserRespository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRespository userRespository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRespository.findUserByUserName(username);
		
		user.orElseThrow(()-> new UsernameNotFoundException("User not found by name "+username));
		
		return user.map(MyUserDetails::new).get();
	}

}
