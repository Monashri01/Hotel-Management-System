package com.owner.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.owner.service.exception.GuestNotFoundException;
import com.owner.service.feignclient.GuestFeignClient;
import com.owner.service.model.GuestDetails;

@RestController
@RequestMapping("/owner")
public class GuestDetailsController {

	@Autowired
	private GuestFeignClient guestFeignClient;

	@GetMapping("/allGuest")
	public ResponseEntity<List<GuestDetails>> showAllGuest(@RequestHeader("Authorization") String token) {

		return guestFeignClient.showAllGuest(token);
	}

	@GetMapping("/guest/{id}")
	public ResponseEntity<GuestDetails> showGuestById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws GuestNotFoundException {

		return guestFeignClient.showById(id, token);
	}

	@PostMapping("/addguest")
	public ResponseEntity<GuestDetails> addGuest(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token) throws GuestNotFoundException {

		return guestFeignClient.addGuest(guestDetails, token);

	}

	@PutMapping("/updateguest")
	public ResponseEntity<GuestDetails> updateGuest(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token)
			throws GuestNotFoundException {

		return guestFeignClient.updateGuest(guestDetails, token);

	}

	@DeleteMapping("/deleteguest/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws GuestNotFoundException {

		return guestFeignClient.deleteGuest(id, token);
	}

}
