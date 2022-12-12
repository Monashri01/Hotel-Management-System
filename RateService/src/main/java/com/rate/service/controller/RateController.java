package com.rate.service.controller;

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

import com.rate.service.exception.RateNotFoundException;
import com.rate.service.model.RateDetails;
import com.rate.service.service.RateServiceImplementation;

@RestController
@RequestMapping("/rate")
public class RateController {
	@Autowired
	private RateServiceImplementation service;
	
	Logger log = LoggerFactory.getLogger(RateController.class);

	
	@GetMapping("/all")
	public ResponseEntity<List<RateDetails>> showAllRateDetails(){
             List<RateDetails> rateDetails = service.showAllRateDetails();
             if(rateDetails.isEmpty()) {
     			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     		}
     		log.debug("Rate are {}",rateDetails);
     		return new ResponseEntity<>(rateDetails, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<RateDetails> showRateDetailsById(@PathVariable("id") int id) throws RateNotFoundException {
		RateDetails rateDetails = service.showRateById(id);
		if(rateDetails!=null) {
			log.debug("Rate Details: {}",rateDetails);
			return new ResponseEntity<>(rateDetails, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@PostMapping("/add")
	public ResponseEntity<RateDetails> addRateDetails(@RequestBody RateDetails rateDetails) throws RateNotFoundException {
		RateDetails rate = service.addRateDetails(rateDetails);
		if(rate!=null) {
			log.debug("Rate Details: {}",rate);
			return new ResponseEntity<>(rate, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@PutMapping("/update")
	public ResponseEntity<RateDetails> updateRateDetails(@RequestBody RateDetails rateDetails) throws RateNotFoundException {
		RateDetails rate = service.updateRateDetails(rateDetails);
		if(rate!=null) {
			log.debug("Rate Details: {}",rate);
			return new ResponseEntity<>(rate, HttpStatus.CREATED);}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCropDetails(@PathVariable("id") int id) throws RateNotFoundException {
		service.deleteRateDetails(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
