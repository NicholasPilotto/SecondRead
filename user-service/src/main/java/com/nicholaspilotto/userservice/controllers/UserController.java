package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserResponseDTO;
import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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

  @Autowired
  private ModelMapper mapper;

  /**
   * Get the list of all users stored into the database.
   * @return List of users.
   */
  @GetMapping("/user")
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<User> users = customerUserService.getAllUsers();
    List<UserResponseDTO> response = Arrays.stream(mapper.map(users, UserResponseDTO[].class)).toList();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Method used to get user by ig.
   * @param id id of the user we are looking for.
   * @return response entity representing user object if user is found, {@code NOT FOUND} otherwise.
   */
  @GetMapping("/user/{id}")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    User user = customerUserService.getUser(id);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  /**
   * Method used to create new user.
   * @param payload user data to store into the database.
   * @return Created user object.
   */
  @PostMapping("/user")
  public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreationDTO payload) {
    User newUser = mapper.map(payload, User.class);
    newUser = customerUserService.createUser(newUser);
    UserResponseDTO response = mapper.map(newUser, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
