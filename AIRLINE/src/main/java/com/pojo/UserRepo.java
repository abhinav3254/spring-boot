package com.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query(nativeQuery = true, value =  "select * from user where username=:username")
	User findUserByUsername(@Param("username") String username);

}
