package com.nicholaspilotto.authenticationservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @PostMapping("/register")
  public ResponseEntity<?> register() {
    return new ResponseEntity<>("You have been registered", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login() {
    return new ResponseEntity<>("You are inside", HttpStatus.OK);
  }
}