package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.models.User;

@Service
public class UserService {
	private List<User> users = new ArrayList<User>();
	
	public UserService() {
		users.add(new User("u8372j","Alice Johnson","alice.johnson@example.com"));
		users.add(new User("p4521x", "John Smith", "john.smith@emailprovider.net"));
		users.add(new User("k6709z", "Emily Davis", "emily.davis123@example.org"));
		users.add(new User("s1234q", "Michael Lee", "michael.lee55@emailservice.com"));
		users.add(new User("r9087w", "Sophia Wilson", "sophia.wilson77@example.net"));
		users.add(new User("t7652a", "William Brown", "william.brown@example.com"));
		users.add(new User("m3987b", "Olivia Martinez", "olivia.martinez@example.org"));
		users.add(new User("n6418c", "Liam Anderson", "liam.anderson@emailprovider.net"));
		users.add(new User("f2356d", "Emma Taylor", "emma.taylor@emailservice.com"));
		users.add(new User("v8721e", "Noah White", "noah.white@example.net"));
		users.add(new User("w1098f", "Ava Harris", "ava.harris123@example.org"));
	}
	
	public List<User> getUsers() {
		return users;
	}
	
}
