package com.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

//@NamedQuery(name = "User.findByEmail",query = "select u from User u where u.email=:email")

@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@Entity(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "status")
	private String status;
	
}
