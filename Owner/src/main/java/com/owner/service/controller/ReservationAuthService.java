package com.owner.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owner.service.feignclient.ReservationAuthClient;
import com.owner.service.model.AuthenticationResponse;



@Service
public class ReservationAuthService {

	@Autowired
	ReservationAuthClient reservationAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = reservationAuthClient.getValidity(token);
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
