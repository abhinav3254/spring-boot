package com.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.jwt.MyUserDetailsService;
import com.pojo.User;
import com.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			if (validateSignUp(requestMap)) {
				// check email exists or not
				User user = userDao.findByEmail(requestMap.get("email"));
				
				if (Objects.isNull(user)) {
					userDao.save(getUser(requestMap));
					return new ResponseEntity<String>("user added",HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("user already exists",HttpStatus.IM_USED);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("SOMETHING WENT WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateSignUp(Map<String, String> requestMap) {
		if (requestMap.containsKey("phoneNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password") && requestMap.containsKey("name")) {
			System.out.println("Here is validateSignUp method --> Validate");
			return true;
		} else { return false; }
	}
	
	private User getUser(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setPhoneNumber(requestMap.get("phoneNumber"));
		user.setRole("user");
		user.setStatus("false");
		
		System.out.println("Here is getUser method --> "+user.toString());
		
		return user;
	}

	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			if (authentication.isAuthenticated()) {
				if (myUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
					System.out.println("Hello "+myUserDetailsService.getUserDetails().getName());
					return new ResponseEntity<String>("Token is :- "+jwtUtils.generateToken(myUserDetailsService.getUserDetails().getEmail(), myUserDetailsService.getUserDetails().getRole()),HttpStatus.OK);
				} else {
					return new ResponseEntity<>("wait for admin approval",HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("BAD CREDENTIALS",HttpStatus.BAD_REQUEST);
	}

}
