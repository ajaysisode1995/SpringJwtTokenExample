package com.mobileprogramming.SpringJwtTokenExample.service;

import java.util.List;

import com.mobileprogramming.SpringJwtTokenExample.entities.User;

public interface UserService {
	//createNewUser
	User registerNewUser(User user);
	//createUser
	User createUser(User user);
	//updateUser
	User UpdateUser(User user,int userId);
	//getUserById
	User getUser(int userId);
	//getAllUser
	List<User> getAllUser();
	//deleteUser
	void deleteUser(int userId);
	
	
	
}
