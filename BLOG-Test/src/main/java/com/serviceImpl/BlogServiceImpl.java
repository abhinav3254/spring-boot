package com.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dao.BlogDao;
import com.jwt.JwtFilter;
import com.jwt.JwtUtils;
import com.pojo.Blog;
import com.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	BlogDao blogDao;

	@Override
	public ResponseEntity<String> addBlog(Map<String, String> requestMap) {
		try {
			if (validateBlog(requestMap)) {
				// add blog
				blogDao.save(blogConfig(requestMap));
				return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Fields can't be empty",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Internal Issue Defualt",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateBlog(Map<String, String> requestMap) {
		if (requestMap.containsKey("title") && requestMap.containsKey("content"))
			return true;
		else return false;
	}
	
	private Blog blogConfig(Map<String, String> requestMap) {
		Blog blog = new Blog();
		Date date;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println("Current user is :- "+jwtUtils.extractUsername(currentPrincipalName));
		blog.setEmail(jwtUtils.extractUsername(currentPrincipalName));
		blog.setBlogContent(requestMap.get("content"));
		blog.setTitle(requestMap.get("title"));
		
		return blog;
		
	}

}
