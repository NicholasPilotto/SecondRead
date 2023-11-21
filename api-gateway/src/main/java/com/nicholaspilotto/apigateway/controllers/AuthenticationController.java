package com.nicholaspilotto.apigateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthenticationController {
  @GetMapping("/login")
  public ResponseEntity<?> login(Authentication authentication) {
    return new ResponseEntity<>("Ok", HttpStatus.OK);
  }
}
