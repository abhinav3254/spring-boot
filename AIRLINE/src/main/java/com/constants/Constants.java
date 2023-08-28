package com.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {
	
	private Constants() {}
	
	
	public static ResponseEntity<String> somethingWentWrong(String message) {
		String messageBuild = "{"
				+ "\n message: "+message+""
						+ "\n}"; 
		return new ResponseEntity<String>(messageBuild,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
