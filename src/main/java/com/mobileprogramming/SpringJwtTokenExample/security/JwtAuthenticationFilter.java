package com.mobileprogramming.SpringJwtTokenExample.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private CustomeUserDetailService cDetailService;
	@Autowired
	private JwtTokenHelper jHelper;
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		//get token
		String requestToken = request.getHeader("Authorization");
		//bearer
		System.out.println(requestToken);
		
		String email=null;
		
		String token=null;
		
		if (requestToken!=null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(6);
			
			try {
				email = this.jHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWt Token");
			}
			catch (ExpiredJwtException e) {
				System.out.println("Jwt Token Has Expired");
			}
			catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
		}
		else {
			System.out.println("Jwt token does not start with bearer");
		}
		
		//Once we get the token ,validate token
		
		if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.cDetailService.loadUserByUsername(email);
			
			if (this.jHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null ,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.
				setDetails(new WebAuthenticationDetailsSource()
				.buildDetails(request));
				
				SecurityContextHolder
				.getContext()
				.setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("username is null or context is null");
			}
		}
		else {
			System.out.println("Invalid Jwt token");
		}
		filterChain.doFilter(request, response);
	}
}
