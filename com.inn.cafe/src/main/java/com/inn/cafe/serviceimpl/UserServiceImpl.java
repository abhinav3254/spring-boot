package com.inn.cafe.serviceimpl;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.consents.CafeConstants;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.pojo.User;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

//	@Slf4j
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		
		try {
		
		System.out.println("inside signup"+requestMap);
		if(validateSignUpMap(requestMap)) {
			
			User user = userDao.findByEmailId(requestMap.get("email"));
			
			if(Objects.isNull(user)) {
				
				userDao.save(getUserFromMap(requestMap));
				return CafeUtils.getResponseEntity("successfully registered", HttpStatus.OK);
				
			} else {
				return CafeUtils.getResponseEntity("email already exists",HttpStatus.BAD_REQUEST);
			}
			
		} else CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return CafeUtils.getResponseEntity(CafeConstants.something_went_wrong, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateSignUpMap(Map<String,String> requestMap) {
		
		if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
		&& requestMap.containsKey("email") && requestMap.containsKey("password")) {
			return true;
		} 
		return false;
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

}
