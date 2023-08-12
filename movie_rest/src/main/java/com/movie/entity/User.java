package com.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;


@NamedQuery(name = "User.getUser",query = "select u from User u where u.username =:username")


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	private String username;
	private String name;
	private String gender;
	private String addresss;
	private String phone;
	private String password;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String name, String gender, String addresss, String phone, String password) {
		super();
		this.username = username;
		this.name = name;
		this.gender = gender;
		this.addresss = addresss;
		this.phone = phone;
		this.password = password;
	}
	public User(long id, String username, String name, String gender, String addresss, String phone,
			String password) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.gender = gender;
		this.addresss = addresss;
		this.phone = phone;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", gender=" + gender + ", addresss="
				+ addresss + ", phone=" + phone + ", password=" + password + "]";
	}
	
	
}
