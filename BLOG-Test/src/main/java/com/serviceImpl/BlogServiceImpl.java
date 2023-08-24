package com.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	@Override
	public ResponseEntity<List<Blog>> getAllBlog() {
		try {
			List<Blog> blogList = blogDao.getAllBlog();
			return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Blog>>(new ArrayList<Blog>(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Blog> getBlogById(String id) {
		try {
			
			Blog blog = blogDao.getBlogById(Integer.parseInt(id));
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Blog>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteById(String id) {
		try {
			 blogDao.deleteById(Integer.parseInt(id));
				return new ResponseEntity<String>("Deleted "+id,HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("can't find blog by id = "+id,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
