package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private final UserDetailsService userDetailsService;
	
	public JwtAuthFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	//@Autowired
	//private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
			String authHeader = request.getHeader("Authorization");
	        if (authHeader == null || !authHeader.startsWith("Bearer")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
			
			
			String token = authHeader.substring(7);
			String username = JwtUtil.extracaoUsername(token);
			
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userdetails = userDetailsService.loadUserByUsername(username);
	            if (JwtUtil.validaToken(token)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	            filterChain.doFilter(request, response);
	        }
			
	}
	
	
	
	
}
