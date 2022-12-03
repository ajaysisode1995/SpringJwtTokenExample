package com.mobileprogramming.SpringJwtTokenExample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobileprogramming.SpringJwtTokenExample.entities.Role;
import com.mobileprogramming.SpringJwtTokenExample.entities.User;
import com.mobileprogramming.SpringJwtTokenExample.repositories.RoleRepository;
import com.mobileprogramming.SpringJwtTokenExample.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository uRepository;
	@Autowired
	private RoleRepository rRepository;
	@Autowired
	private PasswordEncoder pEncoder;

	@Override
	public User registerNewUser(User user) {
		
		//encoded the password
		user.setPassword(this.pEncoder.encode(user.getPassword()));
		
		Role role = this.rRepository.findById(user.getUserId()).get();
		
		user.getRoles().add(role);
		
		User save = this.uRepository.save(user);
		return save;
	}

	@Override
	public User createUser(User user) {
		
		return this.uRepository.save(user);
	}

	@Override
	public User UpdateUser(User user, int userId) {
		User user2 = this.uRepository.findById(userId).get();
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setAddress(user.getAddress());
		return this.uRepository.save(user2);
	}

	@Override
	public User getUser(int userId) {
		User user2 = this.uRepository.findById(userId).get();
		return user2;
	}

	@Override
	public List<User> getAllUser() {
		List<User> findAll = this.uRepository.findAll();
		
		return findAll;
		}

	@Override
	public void deleteUser(int userId) {
		User user2 = this.uRepository.findById(userId).get();
		this.uRepository.delete(user2);
	}
	
	
}
