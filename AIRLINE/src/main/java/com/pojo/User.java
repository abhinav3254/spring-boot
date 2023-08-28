package com.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String gender;
	
	private String address;
	
	private Date dateOfBirth;
	
	private String status;
	
	private String role;
	
}
