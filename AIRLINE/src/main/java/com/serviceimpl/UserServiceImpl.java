package com.serviceimpl;

import com.constants.Constants;
import com.pojo.User;
import com.pojo.UserRepo;
import com.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repo;

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

}
