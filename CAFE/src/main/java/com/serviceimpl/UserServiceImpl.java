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
import com.google.common.base.Strings;
import com.jwt.CustomerUserDetailsService;
import com.jwt.JwtFilter;
import com.jwt.JwtUtils;
import com.pojo.User;
import com.service.UserService;
import com.utils.CafeUtils;
import com.utils.EmailUtils;
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
	EmailUtils emailUtils;
	
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
					sendMailToAllAdmin(requestMap.get("status"),optional.get().getEmail(),userDao.getAllAdmin());
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

	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
		allAdmin.remove(jwtFilter.getCurrentUser());
		if (status != null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser()
			,"Account Enabled","USER :- "+user+" \n is approved by \nADMIN :- "+jwtFilter.getCurrentUser()
			,allAdmin);
		} else {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser()
					,"Account Disabled","USER :- "+user+" \n is disabled by \nADMIN :- "+jwtFilter.getCurrentUser()
					,allAdmin);
		}
	}

	@Override
	public ResponseEntity<String> checkToken() {
		return CafeUtils.getResponseEntity("true", HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
			if (!userObj.equals(null)) {
				if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
					userObj.setPassword(requestMap.get("newPassword"));
					userDao.save(userObj);
					return CafeUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
				}
				
				return CafeUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
			}
			return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			User user = userDao.findByEmail(requestMap.get("email"));
			
			/* Here we are not saying that your email is not registered with us
			 * bcz this will tell the hacker that this email is registered with us
			 * incase if wrong email then user will not find the email
			 * only authentic person will get mail so in every case we will send mail
			 * but actual user will get it
			 * */
			if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
				emailUtils.forgotMail(user.getEmail(), "Credentails By Abhinav-Cafe-Management", user.getPassword());
			}
			return CafeUtils.getResponseEntity("Check Your Mail For Credentails", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
