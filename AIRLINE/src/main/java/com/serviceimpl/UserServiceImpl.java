package com.serviceimpl;

import com.constants.Constants;
import com.dao.UserRepo;
import com.jwt.JwtUtils;
import com.jwt.MyUserDetailsService;
import com.pojo.User;
import com.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			
			User user = repo.findUserByUsername(requestMap.get("username"));
			
			if (Objects.isNull(user)) {
				if (validateSignUp(requestMap)) {
					User user1 = convertUser(requestMap);
					repo.save(user1);
					String messageBuild1 = "{"
							+ "\n message: successfully registered!"
									+ "\n}";
					return new ResponseEntity<String>(messageBuild1,HttpStatus.OK);
				} else {
					String messageBuild = "{"
							+ "\n message: Field's can't be empty"
									+ "\n}"; 
					return new ResponseEntity<String>(messageBuild,HttpStatus.BAD_REQUEST);
				}
			} else {
				String messageBuild = "{"
						+ "\n message: Username Already Taken"
								+ "\n}"; 
				return new ResponseEntity<String>(messageBuild,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.somethingWentWrong("something went wrong!!");
	}
	
	private boolean validateSignUp(Map<String, String> requestMap) {
		
		if (requestMap.containsKey("username") && requestMap.containsKey("email")
				&& requestMap.containsKey("password") && requestMap.containsKey("phone")
				) {
			return true;
		} else { return false; }
		
	}
	
	private User convertUser(Map<String, String> requestMap) {
		User user = new User();
		
		user.setUsername(requestMap.get("username"));
		user.setPassword(requestMap.get("password"));
		user.setName(requestMap.get("name"));
		user.setEmail(requestMap.get("email"));
		user.setPhone(requestMap.get("phone"));
		user.setAddress(requestMap.get("address"));
		user.setGender(requestMap.get("gender"));
		user.setStatus("false");
		user.setRole("user");
		
		return user;
	}

	
	
	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {
			User user = repo.findUserByUsername(requestMap.get("username"));
			if (Objects.isNull(user)) {
				String messageBuild = "{"
						+ "\n message: user not found"
								+ "\n}"; 
				return new ResponseEntity<String>(messageBuild,HttpStatus.BAD_REQUEST); 
			} else {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"), requestMap.get("password")));
				if (authentication.isAuthenticated()) {
					if (myUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
						return new ResponseEntity<String>("{ \"token\" : \""+jwtUtils.generateToken(myUserDetailsService.getUserDetails().getUsername(), myUserDetailsService.getUserDetails().getRole())+"\"}",HttpStatus.OK);
					} else {
						String messageBuild = "{"
								+ "\n message: wait for admin approval"
										+ "\n}"; 
						return new ResponseEntity<>(messageBuild,HttpStatus.OK);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.somethingWentWrong("something went wrong!!");
	}
	
}
