package com.mobileprogramming.SpringJwtTokenExample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobileprogramming.SpringJwtTokenExample.entities.User;
import com.mobileprogramming.SpringJwtTokenExample.repositories.UserRepository;
@Service
public class CustomeUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository uRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.uRepository.findByEmail(username).get();
		return (UserDetails) user;
	}

}
