package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Blog;

public interface BlogDao extends JpaRepository<Blog, Integer> {

}
