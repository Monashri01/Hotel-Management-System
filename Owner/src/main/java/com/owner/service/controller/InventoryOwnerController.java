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


import com.owner.service.exception.InventoryNotFoundException;
import com.owner.service.feignclient.InventoryFeignClient;
import com.owner.service.model.Inventory;

@RestController
@RequestMapping("/owner")
public class InventoryOwnerController {
	@Autowired
	private InventoryFeignClient inventoryClient;
	
	@Autowired
	private InventoryAuthService inventoryAuthService; 
	
	@GetMapping("/allInv")
    public ResponseEntity<List<Inventory>> showAllInventory(@RequestHeader("Authorization") String token){
		try {
            if (inventoryAuthService.isSessionValid(token)) {
		
             return inventoryClient.showAllInventory();
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
        }
	@GetMapping("/inv/{id}")
    public ResponseEntity<Inventory> showById(@PathVariable("id") int id,@RequestHeader("Authorization") String token ) throws InventoryNotFoundException {
		try {
            if (inventoryAuthService.isSessionValid(token)) {
            return inventoryClient.showById(id);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
    }
	}
	
	@PostMapping("/addInventory")
	public ResponseEntity<Inventory> addInventoryDetails(@RequestBody Inventory inventoryDetails,@RequestHeader("Authorization") String token ) throws InventoryNotFoundException {
		try {
            if (inventoryAuthService.isSessionValid(token)) {
		return inventoryClient.addInventoryDetails(inventoryDetails);}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            }
            catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
            }
	}
	@PutMapping("/updateInventory")
	public ResponseEntity<Inventory> updateInventoryDetails(@RequestBody Inventory inventoryDetails,@RequestHeader("Authorization") String token) throws InventoryNotFoundException{
		try {
            if (inventoryAuthService.isSessionValid(token)) {
		return inventoryClient.updateInventoryDetails(inventoryDetails);}
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
	}
	@DeleteMapping("/deleteInventory/{id}")
	public ResponseEntity<String> deleteInventoryDetails(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws InventoryNotFoundException{
		try {
            if (inventoryAuthService.isSessionValid(token)) {
			return inventoryClient.deleteInventoryDetails(id);
	}
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
        }
	}

}
