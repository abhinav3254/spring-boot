package com.inn.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
	
	// made the constructor private so that we can't create object of this class
	private CafeUtils() {}
	
	public static ResponseEntity<String> getResponseEntity(String message,HttpStatus httpStatus) {
		return new ResponseEntity<String>("{\"message\":\""+message+"\"}",httpStatus);
	}
	
}
