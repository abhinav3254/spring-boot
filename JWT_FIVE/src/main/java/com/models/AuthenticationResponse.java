package com.models;

public class AuthenticationResponse {
	// this is json web token which we are going to sent
	// back to the client
	private final String jwt;
	
	public AuthenticationResponse(String jwt) { this.jwt = jwt; }
	
	public String getJwt() { return this.jwt; }
}
