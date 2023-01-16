package com.roomservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.roomservice.controller.RoomController;
import com.roomservice.exception.RoomNotFoundException;
import com.roomservice.model.Room;
import com.roomservice.service.RoomAuthService;
import com.roomservice.service.RoomServiceImplementation;

@SpringBootTest
class RoomControllerTest {

	@Autowired
	private RoomController controller;

	@MockBean
	private RoomServiceImplementation roomService;

	@MockBean
	private RoomAuthService authService;
	List<Room> reservation = new ArrayList<>();

	String token = "token";

	@Test
	public void ShowAllRoomTestController() throws RoomNotFoundException {

		List<Room> room = new ArrayList<>();

		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);

		room.add(r);

		when(roomService.showAllRoom()).thenReturn(room);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(1, controller.showAllRoom(token).getBody().size());
	}

	@Test
	public void ShowRoomByIdControllerTest() throws RoomNotFoundException {

		List<Room> room = new ArrayList<>();

		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);

		room.add(r);

		when(roomService.showRoomById(1)).thenReturn(r);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(r, controller.showRoomDetailsById(1, token).getBody());
	}

	@Test
	public void addRoomControllerTest() throws RoomNotFoundException {

		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);

		when(roomService.addRoom(r)).thenReturn(r);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(r, controller.addRoomDetails(r, token).getBody());
	}

	@Test
	public void updateRoomControllerTest() throws RoomNotFoundException {

		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);

		when(roomService.updateRoom(r)).thenReturn(r);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(r, controller.updateRoomDetails(r, token).getBody());
	}

	@Test
	public void deleteRoomControllerTest() throws RoomNotFoundException {
		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);

		when(roomService.deleteRoom(1)).thenReturn("Deleted Successfully");
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals("Room Deleted Successfully", controller.deleteRoomDetails(1, token).getBody());
	}
}