package com.mobileprogramming.SpringJwtTokenExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mobileprogramming.SpringJwtTokenExample.repositories.RoleRepository;
@SpringBootApplication
public class SpringJwtTokenExampleApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder pEncoder;
	@Autowired
	private RoleRepository rRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtTokenExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.pEncoder.encode("ajay"));
		
	}

}
