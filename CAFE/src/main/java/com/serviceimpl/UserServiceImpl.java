package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.constants.CafeConstants;
import com.dao.UserDao;
import com.jwt.CustomerUserDetailsService;
import com.jwt.JwtFilter;
import com.jwt.JwtUtils;
import com.pojo.User;
import com.service.UserService;
import com.utils.CafeUtils;
import com.wrapper.UserWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		
		try {

			
			log.info("Inside SignUp {}",requestMap);
			
			if (validateSignUpMap(requestMap)) {
				
				User user = userDao.findByEmailId(requestMap.get("email"));
				if (Objects.isNull(user)) {
					
					userDao.save(getUserFromMap(requestMap));
					
					return CafeUtils.getResponseEntity("successfully registered", HttpStatus.OK);
					
				}
				else return CafeUtils.getResponseEntity("Email Already Exists",HttpStatus.BAD_REQUEST);
				
			} else return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
		&& requestMap.containsKey("email") && requestMap.containsKey("password")) {
			return true;
		} else return false;
	}
	
	private User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		log.info("inside login of UserServiceImpl class");
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
					);
			if (auth.isAuthenticated()) {
				if (customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
					return new ResponseEntity<String>("{\"token\":\""
				+jwtUtils.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
						customerUserDetailsService.getUserDetail().getRole())+"\"}",HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("{\"message\":\""+"Wait For Admin Approval."+"\"}",HttpStatus.BAD_REQUEST);
				}
			}
			
		} catch (Exception e) {
			log.error("{}",e);
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",HttpStatus.BAD_REQUEST);
		
	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
			
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<>(userDao.getAllUser(),HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			
			if (jwtFilter.isAdmin()) {
				Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
				if (!optional.isEmpty()) {
					userDao.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
					return CafeUtils.getResponseEntity("user status updated successfully", HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity("User Id doesn't exist", HttpStatus.OK);
				}
			} else {
				return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
