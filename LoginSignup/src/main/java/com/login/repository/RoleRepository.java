package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.entity.Role;
import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
