package com.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * AuthenticationEntryPoint. Method of this class is called whenever as 
 * exception is thrown due to unauthenticated user trying to access the 
 * resource that required authentication.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	
	/* Ye commence method tab call hoga jab koi unauthenticated person
	 * apne api ko touch kre tab ek exception hoga aur apna ye method automatically
	 * call ho jayega
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.println("Login First\nAccess denied "+authException.getMessage());
	}

}
