package com.owner.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.owner.service.model.AuthenticationResponse;

@FeignClient(name="Authentication", url= "http://localhost:9999/auth")
public interface GuestAuthClient {
    
    @GetMapping("/validate")
    public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token) ;
    
    }