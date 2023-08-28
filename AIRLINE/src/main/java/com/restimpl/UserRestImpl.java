package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.rest.UserRest;
import com.service.UserService;

@RestController
public class UserRestImpl implements UserRest {
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			userService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.somethingWentWrong("something went wrong!"); 
	}

}
