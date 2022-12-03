package com.mobileprogramming.SpringJwtTokenExample.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
      public static final long JWT_TOKEN_VALIDITY=5*60*60;
	
	private String secret="jwtTokenKey";
	//retrieve username token from jwt token
	public	String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
		}
	
	//retrive expiration date from jwt token
	
	public Date getExpirationDateFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
		}
	
	//retrieve claim from jwttoken 
	public <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver) {
		
			Claims claims = getAllClaimsFromToken(token);
			
		return claimsResolver
				.apply(claims);
	}
	
	//for retriving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}
	
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
	final Date expiration = (Date) getExpirationDateFromToken(token);/////////
		return expiration
			.before(new Date());
		}
	
	//generate token from user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims=new HashMap<>();
		return 
		doGenerateToken(claims,
				userDetails.getUsername());/////////
		
	}
	
	//while creating token
	//1.Define claims of the token like issuer,Expiration,subject and the ID
	//2.Sign the JWT using the HS512 algorithm and secrete key.
	//3.According to Jws Compact Serialization
	//compaction of the jWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims,String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		}
	
	//validate token
	public Boolean validateToken(String token,UserDetails userDetails) {
	final String username = getUsernameFromToken(token);
		return (username
				.equals(userDetails.getUsername())
				&& !isTokenExpired(token));/////////
		
	}
}
