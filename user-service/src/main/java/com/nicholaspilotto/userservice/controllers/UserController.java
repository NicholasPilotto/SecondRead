package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserResponseDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserUpdateDTO;
import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import com.nicholaspilotto.userservice.utilities.Utility;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
   * @param pageable pageable filters.
   * @return List of users.
   */
  @GetMapping()
  public ResponseEntity<?> getAllUsers(final Pageable pageable) {
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
    User user = customerUserService.getUser(id)
                                   .orElse(null);

    if (user == null) {
      return new ResponseEntity<>("User with provided id does not exist.", HttpStatus.NOT_FOUND);
    }

    UserResponseDTO response = mapper.map(user, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Method used to create new user.
   * @param payload user data to store into the database.
   * See {@link com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO}
   * @return Created user object.
   */
  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody UserCreationDTO payload) {
    try {
      payload.setPassword(Utility.hashMD5(payload.getPassword()));
      User newUser = mapper.map(payload, User.class);
      newUser = customerUserService.createUser(newUser);
      UserResponseDTO response = mapper.map(newUser, UserResponseDTO.class);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } catch (NoSuchAlgorithmException exception) {
      return new ResponseEntity<>(
        "Cannot save user due to internal error",
        HttpStatus.INTERNAL_SERVER_ERROR
      );
    }
  }

  /**
   * Update existing user.
   * @param id id of the user that needs to be updated.
   * @param payload New user data.
   * See {@link com.nicholaspilotto.userservice.models.dtos.user.UserUpdateDTO}
   * @return Updated user data.
   */
  @PatchMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable Long id,
    @RequestBody UserUpdateDTO payload
  ) {
    User existing = customerUserService.getUser(id).orElse(null);

    if (existing == null) {
      return new ResponseEntity<>(
        "User with provided ID does not exist",
        HttpStatus.BAD_REQUEST
      );
    }

    existing = payload.overwrite(existing);

    existing = customerUserService.update(existing);
    UserResponseDTO response = mapper.map(existing, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/generate-fake-data/{number}")
  public ResponseEntity<?> generateFakeData(@PathVariable int number) {
    List<User> fakeUsers = Utility.generateFakeUsers(number);
    List<User> result = new ArrayList<>();

    for (User fakeUser : fakeUsers) {
      result.add(customerUserService.createUser(fakeUser));
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
