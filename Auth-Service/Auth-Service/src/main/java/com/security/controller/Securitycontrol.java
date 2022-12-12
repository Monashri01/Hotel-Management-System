package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.Jwtutil.JwtUtil;
import com.security.entity.User;
import com.security.model.AuthenticationRequest;
import com.security.model.AuthenticationResponse;
import com.security.repo.UserRepo;
import com.security.service.MyUserDetailsService;

@RestController
@RequestMapping("/auth")
public class Securitycontrol {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepo repo;

	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping("/register")
	public String showRegister(@RequestBody User user) {
		User u =repo.insert(user);
	      if(u!=null)
	    	  return "Registered Successfully";
	      return "Not Registered";
	}
	
	@PostMapping("/login")
	public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password ", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		return jwtTokenUtil.generateToken(userDetails);

		
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token) {
		AuthenticationResponse response = new AuthenticationResponse();
		try {
			if (token == null) {
				response.setValid(false);
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			} else {
				String token1 = token.substring(7);

				if (jwtTokenUtil.verifyToken(token1)) {

					String name = jwtTokenUtil.extractUsername(token1);
					User user = userDetailsService.loadByUserRole(name);
					response.setName(name);
					response.setRole(user.getRole());
					response.setValid(true);
				} else {
					response.setValid(false);
					return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
