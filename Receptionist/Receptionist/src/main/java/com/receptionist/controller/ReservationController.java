package com.receptionist.controller;

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

import com.receptionist.exception.ReservationNotFoundException;
import com.receptionist.feignclient.ReservationFeignClient;
import com.receptionist.model.Reservation;

@RestController
@RequestMapping("/receptionist")
public class ReservationController {
	@Autowired
	private ReservationFeignClient reservationClient;

	@Autowired
	private ReservationAuthService reservationAuthService;

	@GetMapping("/allres")
	public ResponseEntity<List<Reservation>> showAllReservation(@RequestHeader("Authorization") String token) {
		try {
			if (reservationAuthService.isSessionValid(token)) {
				return reservationClient.showAllReservation();
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/res/{id}")
	public ResponseEntity<Reservation> showReservationById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (reservationAuthService.isSessionValid(token)) {
				return reservationClient.showById(id);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addreservation")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (reservationAuthService.isSessionValid(token)) {
				return reservationClient.addReservation(reservation);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updatereservation")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (reservationAuthService.isSessionValid(token)) {
				return reservationClient.updateReservation(reservation);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@DeleteMapping("/deleteres/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (reservationAuthService.isSessionValid(token)) {
				return reservationClient.deleteReservation(id);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}
