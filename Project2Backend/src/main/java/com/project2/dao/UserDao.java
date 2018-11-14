package com.project2.dao;

import org.springframework.stereotype.Service;

import com.project2.models.User;

@Service
public interface UserDao {
	void RegisterUser(User user);

	boolean isEmailUnique(String email);

	User login(User user);

	// i/p email and password ,o/p values for all properties if valid input,else
	// null
	// valid credentials -o/p user object
	// invalid credentials - o/p null object
	void updateUser(User user);

	User getUser(String email);

}
