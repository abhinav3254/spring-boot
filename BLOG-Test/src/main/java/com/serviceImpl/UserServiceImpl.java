package com.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			if (validateSignUp(requestMap)) {
				// check email exists or not
				User user = userDao.findByEmail(requestMap.get("email"));
				if (Objects.nonNull(user)) {
					return new ResponseEntity<String>("user already exists",HttpStatus.IM_USED);
				} else {
					userDao.save(getUser(requestMap));
					return new ResponseEntity<String>("user added",HttpStatus.OK);
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

}
