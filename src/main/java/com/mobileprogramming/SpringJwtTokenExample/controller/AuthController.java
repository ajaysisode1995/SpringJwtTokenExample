package com.mobileprogramming.SpringJwtTokenExample.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mobileprogramming.SpringJwtTokenExample.entities.User;
import com.mobileprogramming.SpringJwtTokenExample.payload.JwtAuthRequest;
import com.mobileprogramming.SpringJwtTokenExample.payload.JwtAuthResponse;
import com.mobileprogramming.SpringJwtTokenExample.security.JwtTokenHelper;
import com.mobileprogramming.SpringJwtTokenExample.service.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private JwtTokenHelper jHelper;
	@Autowired
	private UserDetailsService uDetailsService;
	@Autowired
	private AuthenticationManager aManager;
	@Autowired
	private UserService uService;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		authenticate(request.getEmail(),request.getPassword());
		
		UserDetails userDetails = this.uDetailsService.loadUserByUsername(request.getEmail());
		
		String token = this.jHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}


	private void authenticate(String email,String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = 
		new UsernamePasswordAuthenticationToken(email, password);
		
		try {
			this.aManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new Exception("Invalid username or password");
		}
		
	}
	
	//register new Api
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User registerNewUser = this.uService.registerNewUser(user);
		return new ResponseEntity<User>(registerNewUser,HttpStatus.CREATED);
		
	}
}
