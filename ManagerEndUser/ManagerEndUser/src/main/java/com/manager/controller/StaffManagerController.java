package com.manager.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.manager.exception.StaffNotFoundException;
import com.manager.feignclient.StaffFiegnClient;
import com.manager.model.Staff;


@RestController
@RequestMapping("/manager")

public class StaffManagerController {
	@Autowired
	private StaffFiegnClient staffClient;
	
	@Autowired
	private StaffAuthService staffAuthService;
	
	@GetMapping("/allStaff")
    public ResponseEntity<List<Staff>> showAllStaff(@RequestHeader("Authorization") String token){
        try {
            if (staffAuthService.isSessionValid(token)) {
             return staffClient.showAllStaff();
    }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
        }
	@GetMapping("/staff/{id}")
    public ResponseEntity<Staff> showRoomById(@PathVariable("id") int id,@RequestHeader("Authorization") String token)
            throws StaffNotFoundException {
        try {
            if (staffAuthService.isSessionValid(token)) {
            return staffClient.showById(id);
            }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	
	@PostMapping("/addStaff")
	public ResponseEntity<Staff> addStaffDetails(@RequestBody Staff staffDetails,@RequestHeader("Authorization") String token) throws StaffNotFoundException {
		try {
            if (staffAuthService.isSessionValid(token)) {
		return staffClient.addStaffDetails(staffDetails);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	@PutMapping("/updateStaff")
	public ResponseEntity<Staff> updateStaffDetails(@RequestBody Staff staffDetails,@RequestHeader("Authorization") String token) throws StaffNotFoundException{
		try {
            if (staffAuthService.isSessionValid(token)) {
		return staffClient.updateStaffDetails(staffDetails);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	@DeleteMapping("/deleteStaff/{id}")
	public ResponseEntity<String> deleteStaffDetails(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws StaffNotFoundException{
		try {
            if (staffAuthService.isSessionValid(token)) {
		return staffClient.deleteStaffDetails(id);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
}
