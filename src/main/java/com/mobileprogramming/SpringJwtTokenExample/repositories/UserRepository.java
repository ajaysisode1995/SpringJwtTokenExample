package com.mobileprogramming.SpringJwtTokenExample.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobileprogramming.SpringJwtTokenExample.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email,String password);
	
	Optional<User> findByEmail(String email);
}
