package com.mobileprogramming.SpringJwtTokenExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileprogramming.SpringJwtTokenExample.entities.User;
import com.mobileprogramming.SpringJwtTokenExample.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	@Autowired
	private UserService uService;
	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user2 = this.uService.createUser(user);
		return new ResponseEntity<User>(user2,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable int userId){
		User updateUser = this.uService.UpdateUser(user, userId);
		return new ResponseEntity<User>(updateUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/getUser/{userId})")
	public ResponseEntity<User> getUser(@PathVariable int userId){
		User user = this.uService.getUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> list = this.uService.getAllUser();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		this.uService.deleteUser(userId);
		return new ResponseEntity<String>("user is deleted successfully",HttpStatus.OK);
		
	}
}
