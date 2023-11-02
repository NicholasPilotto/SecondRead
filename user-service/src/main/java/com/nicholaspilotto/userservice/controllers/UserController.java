package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserResponseDTO;
import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a controller class used to interact
 * with user data.
 */
@RestController
@RequestMapping("/user")
public class UserController {
  /**
   * CustomerUserService class.
   * It is used to manage user data and
   * interact with database.
   */
  private final CustomerUserService customerUserService;

  /**
   * ModelMapper utility used to map entities into dto
   * and vice-versa.
   */
  private final ModelMapper mapper;

  @Autowired
  public UserController(
    CustomerUserService customerUserService,
    ModelMapper mapper
  ) {
    this.customerUserService = customerUserService;
    this.mapper = mapper;
  }

  /**
   * Get the list of all users stored into the database.
   * @param page number of the desired page. Default value is 0.
   * @param size number of elements in the page. Default value is 20.
   * @return List of users.
   */
  @GetMapping()
  public ResponseEntity<?> getAllUsers(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size
  ) {
    Pageable pageable = PageRequest.of(page, size);

    List<User> users = customerUserService.getAllUsers(pageable).getContent();
    List<UserResponseDTO> response = Arrays.stream(mapper.map(users, UserResponseDTO[].class)).toList();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get the number of user store into the database.
   * @return Number of user store into the database.
   */
 @GetMapping("/count")
 public ResponseEntity<Long> count() {
    Long count = customerUserService.count();
    return new ResponseEntity<>(count, HttpStatus.OK);
 }

  /**
   * Method used to get user by ig.
   * @param id id of the user we are looking for.
   * @return response entity representing user object if user is found, {@code NOT FOUND} otherwise.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    User user = customerUserService.getUser(id).orElse(null);

    if (user == null) {
      return new ResponseEntity<>("User with provided id does not exist.", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  /**
   * Method used to create new user.
   * @param payload user data to store into the database.
   * @return Created user object.
   */
  @PostMapping()
  public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreationDTO payload) {
    User newUser = mapper.map(payload, User.class);
    newUser = customerUserService.createUser(newUser);
    UserResponseDTO response = mapper.map(newUser, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
