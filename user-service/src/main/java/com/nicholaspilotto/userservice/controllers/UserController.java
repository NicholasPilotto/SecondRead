package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.services.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a controller class used to interact
 * with user data.
 */
@RestController
public class UserController {
  @Autowired
  private RestTemplate restTemplate;

  /**
   * CustomerUserService class.
   * It is used to manage user data and
   * interact with database.
   */
  @Autowired
  private CustomerUserService customerUserService;

  /**
   * Method used to test current controller.
   * @return response entity with test data.
   */
  @GetMapping("/user/test")
  public ResponseEntity<String> UserTest() {
    return new ResponseEntity<>(customerUserService.getUser(), HttpStatus.OK);
  }
}
