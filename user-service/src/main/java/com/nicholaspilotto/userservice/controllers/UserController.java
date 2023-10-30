package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
   * Get the list of all users stored into the database.
   * @return List of users.
   */
  @GetMapping("/user")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = customerUserService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  /**
   * Method used to get user by ig.
   * @param id id of the user we are looking for.
   * @return response entity representing user object if user is found, {@code NOT FOUND} otherwise.
   */
  @GetMapping("/user/{id}")
  public ResponseEntity<User> GetById(@PathVariable Long id) {
    User user = customerUserService.getUser(id);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
