package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pojo.Blog;

public interface BlogDao extends JpaRepository<Blog, Integer> {
	
	@Query(nativeQuery = true,value = "select * from blog;")
	List<Blog> getAllBlog();
	
	@Query(nativeQuery = true,value = "select * from blog where id=:id")
	Blog getBlogById(@Param("id")int id);
	
	/*
	@Query(nativeQuery = true,value = "delete from blog where id=:id")
	Integer deleteById(@Param("id")int id);
	*/
	
}
