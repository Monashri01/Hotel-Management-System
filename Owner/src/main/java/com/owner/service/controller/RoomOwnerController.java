package com.owner.service.controller;

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

import com.owner.service.exception.RoomNotFoundException;
import com.owner.service.feignclient.RoomFeignClient;
import com.owner.service.model.Room;


@RestController
@RequestMapping("/owner")
public class RoomOwnerController {
	@Autowired
	private RoomFeignClient roomClient;
	
	@Autowired
	private RoomAuthService roomAuthService;
	
	@GetMapping("/allRoom")
    public ResponseEntity<List<Room>> showAllRoom(@RequestHeader("Authorization") String token){
		try {
            if (roomAuthService.isSessionValid(token)) {
		
             return roomClient.showAllRoom();
            }
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            }
    }
    @GetMapping("/room/{id}")
    public ResponseEntity<Room> showById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws RoomNotFoundException {
    	try {
            if (roomAuthService.isSessionValid(token)) {
            return roomClient.showById(id);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
    }
	
	@PostMapping("/addRoomDetails")
	public ResponseEntity<Room> addRoomDetails(@RequestBody Room roomDetails,@RequestHeader("Authorization") String token) throws RoomNotFoundException {
		try {
            if (roomAuthService.isSessionValid(token)) {
		return roomClient.addRoomDetails(roomDetails);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	@PutMapping("/updateRoomDetails")
	public ResponseEntity<Room> updateRoomDetails(@RequestBody Room roomDetails,@RequestHeader("Authorization") String token) throws RoomNotFoundException{
		try {
            if (roomAuthService.isSessionValid(token)) {
		return roomClient.updateRoomDetails(roomDetails);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	@DeleteMapping("/deleteRoomDetails/{id}")
	public ResponseEntity<String> deleteRoomDetails(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws RoomNotFoundException{
		try {
            if (roomAuthService.isSessionValid(token)) {
		return roomClient.deleteRoomDetails(id);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
}
