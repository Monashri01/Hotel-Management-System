package com.owner.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owner.service.feignclient.InventoryAuthClient;
import com.owner.service.model.AuthenticationResponse;



@Service
public class InventoryAuthService {
	@Autowired
	InventoryAuthClient inventoryAuthClient;
	
	public boolean isSessionValid(String token) {

	       AuthenticationResponse authenticationResponse = inventoryAuthClient.getValidity(token);
	        if (authenticationResponse == null) {
	            throw new RuntimeException("Authentication reponse returned as  NULL");
	        }
	       String role = authenticationResponse.getRole().substring(5);
	       if (role.equals("OWNER"))
	            return true;
	       else
	           return false;
	   }
}