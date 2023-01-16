
package com.roomservice.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.roomservice.exception.RoomNotFoundException;
import com.roomservice.model.Room;
import com.roomservice.service.RoomAuthService;
import com.roomservice.service.RoomServiceImplementation;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	
	@Autowired
	private RoomServiceImplementation service;

	@Autowired
	private RoomAuthService roomAuthService;

	Logger log = LoggerFactory.getLogger(RoomController.class);

	@GetMapping("/all")
	public ResponseEntity<List<Room>> showAllRoom(@RequestHeader("Authorization") String token){
		try {
            if (roomAuthService.isSessionValid(token)) {
             List<Room> room = service.showAllRoom();
             if(room.isEmpty()) {
     			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     		}
     		log.debug("Rooms are {}",room);
     		return new ResponseEntity<>(room, HttpStatus.OK);
            }
     		 throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            }
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> showRoomDetailsById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws RoomNotFoundException {
		try {
			if (roomAuthService.isSessionValid(token)) {
				Room room = service.showRoomById(id);
				if (room != null) {
					log.debug("Room: {}", room);
					return new ResponseEntity<>(room, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PostMapping("/addroom")
	public ResponseEntity<Room> addRoomDetails(@RequestBody Room room, @RequestHeader("Authorization") String token)
			throws RoomNotFoundException {
		try {
			if (roomAuthService.isSessionValid(token)) {

				@SuppressWarnings("unused")
				Room rm = service.addRoom(room);
				if (room != null) {
					log.debug("Rooms: {}", room);
					return new ResponseEntity<>(room, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updateroom")
	public ResponseEntity<Room> updateRoomDetails(@RequestBody Room room, @RequestHeader("Authorization") String token)
			throws RoomNotFoundException {
		try {
			if (roomAuthService.isSessionValid(token)) {

				@SuppressWarnings("unused")
				Room rm = service.updateRoom(room);
				if (room != null) {
					log.debug("Rooms: {}", room);
					return new ResponseEntity<>(room, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/deleteroom/{id}")
	public ResponseEntity<String> deleteRoomDetails(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws RoomNotFoundException {
		try {
			if (roomAuthService.isSessionValid(token)) {

				service.deleteRoom(id);
				return new ResponseEntity<>("Room Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}

