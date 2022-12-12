package com.owner.service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.owner.service.exception.StaffNotFoundException;
import com.owner.service.model.Staff;

@FeignClient(name="Staff-Service" , url="http://localhost:8085/staff")
public interface StaffFiegnClient {
	@GetMapping("/all")
    public ResponseEntity<List<Staff>> showAllStaff();

	@GetMapping("/{id}")
    public ResponseEntity<Staff> showById(@PathVariable("id") int id) throws StaffNotFoundException;


	@PostMapping("/add")
	public ResponseEntity<Staff> addStaffDetails(@RequestBody Staff staffDetails) throws StaffNotFoundException;
		
	
	@PutMapping("/update")
	public ResponseEntity<Staff> updateStaffDetails(@RequestBody Staff staff) throws StaffNotFoundException;
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStaffDetails(@PathVariable("id") int id) throws StaffNotFoundException;
	
}