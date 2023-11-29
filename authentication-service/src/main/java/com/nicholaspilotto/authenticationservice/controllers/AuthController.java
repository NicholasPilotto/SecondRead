package com.nicholaspilotto.authenticationservice.controllers;

import com.nicholaspilotto.authenticationservice.models.AuthenticationResponse;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest payload) {
    AuthenticationResponse response = authService.register(payload);

    if (response == null) {
      return new ResponseEntity<>("Cannot register this user.", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
