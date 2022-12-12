package com.guestservice.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestservice.exception.GuestNotFoundException;
import com.guestservice.model.GuestDetails;
import com.guestservice.service.GuestDetailsServiceImplementation;

@RestController
@RequestMapping("/guest")
public class GuestDetailsController {
	
	@Autowired
	private GuestDetailsServiceImplementation service;
	
	Logger log = LoggerFactory.getLogger(GuestDetailsController.class);
	
	@GetMapping("/all")
	public ResponseEntity<List<GuestDetails>> showAllGuestDetails(){
             List<GuestDetails> guestDetails = service.showAllGuestDetails();
             if(guestDetails.isEmpty()) {
     			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     		}
     		log.debug("Guest are {}",guestDetails);
     		return new ResponseEntity<>(guestDetails, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GuestDetails> showGuestDetailsById(@PathVariable("id") int id) throws GuestNotFoundException {
		GuestDetails guestDetails = service.showGuestById(id);
		if(guestDetails!=null) {
			log.debug("Guest Details: {}",guestDetails);
			return new ResponseEntity<>(guestDetails, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/addguest")
	public ResponseEntity<GuestDetails> addGuestDetails(@RequestBody GuestDetails guestDetails) throws GuestNotFoundException {
		GuestDetails guest = service.addGuestDetails(guestDetails);
		if(guest!=null) {
			log.debug("Guest Details: {}",guest);
			return new ResponseEntity<>(guest, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateguest")
	public ResponseEntity<GuestDetails> updateCroDetails(@RequestBody GuestDetails guestDetails) throws GuestNotFoundException {
		GuestDetails guest = service.updateGuestDetails(guestDetails);
		if(guest!=null) {
			log.debug("Guest Details: {}",guest);
			return new ResponseEntity<>(guest, HttpStatus.CREATED);}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteguest/{id}")
	public ResponseEntity<String> deleteGuestDetails(@PathVariable("id") int id) throws GuestNotFoundException {
		service.deleteGuestDetails(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
