package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.annotations.AuthorizedRoles;
import com.nicholaspilotto.userservice.filters.UserFilters;
import com.nicholaspilotto.userservice.models.dtos.errors.ErrorResponse;
import com.nicholaspilotto.userservice.models.dtos.status.PongDto;
import com.nicholaspilotto.userservice.models.dtos.user.LoginCredential;
import com.nicholaspilotto.userservice.models.dtos.user.UserCreationDto;
import com.nicholaspilotto.userservice.models.dtos.user.UserResponseDto;
import com.nicholaspilotto.userservice.models.dtos.user.UserUpdateDto;
import com.nicholaspilotto.userservice.models.entities.Role;
import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import com.nicholaspilotto.userservice.utilities.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  public UserController(
    CustomerUserService customerUserService,
    ModelMapper mapper) {
    this.customerUserService = customerUserService;
    this.mapper = mapper;
  }

  /**
   * Get the list of all users stored into the database.
   *
   * @param pageable pageable filters.
   * @return List of users.
   */
  @GetMapping()
  @AuthorizedRoles(authorized = { Role.ADMIN, Role.CUSTOMER})
  public ResponseEntity<?> getAllUsers(final Pageable pageable, final UserFilters filters) {
    List<User> users = customerUserService.getAllUsers(pageable, filters.toPredicate()).getContent();

    List<UserResponseDto> response = Arrays.stream(mapper.map(users, UserResponseDto[].class)).toList();
    logger.info("Users have been requested.");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get the number of user store into the database.
   *
   * @return Number of user store into the database.
   */
  @GetMapping("/count")
  @AuthorizedRoles(authorized = { Role.ADMIN, Role.CUSTOMER })
  public ResponseEntity<Long> count(final UserFilters filters) {
    Long count = customerUserService.count(filters.toPredicate());
    logger.info("Count has been request. The result is: %s".formatted(count));
    return new ResponseEntity<>(count, HttpStatus.OK);
  }

  /**
   * Method used to get user by id.
   *
   * @param id id of the user we are looking for.
   * @return response entity representing user object if user is found, {@code NOT FOUND} otherwise.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    User user = customerUserService.getUserById(id)
                                   .orElse(null);

    if (user == null) {
      logger.warn("User with id %s has not been found".formatted(id));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND,
        "User with provided id does not exist."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    logger.info("User with id %s has been found.".formatted(id));
    UserResponseDto response = mapper.map(user, UserResponseDto.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get {@link User} by email.
   *
   * @param email {@link User} email.
   *
   * @return {@link User} with {@code email}.
   */
  @GetMapping("/email/{email}")
  public ResponseEntity<?> getByEmail(@PathVariable @Email String email) {
    User user = customerUserService.getUserByEmail(email).orElse(null);

    if (user == null) {
      logger.warn("User with email %s has not been found".formatted(email));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND,
        "User with provided email does not exist."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    logger.info("User with email %s has been found".formatted(email));
    UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  /**
   * Ping pong method. This method responds only if the service is up.
   *
   * @return if the service is up, it responds with {@code pong}.
   */
  @GetMapping("/ping")
  public ResponseEntity<?> pong() {
    return new ResponseEntity<>(new PongDto(), HttpStatus.OK);
  }

  /**
   * Login {@link User} into the system.
   *
   * @param credential {@link User} credential, such as {@code email} and {@code password}.
   *
   * @return {@link User} data if the credentials are correct, otherwise, {@code NO_CONTENT} response.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @NotNull LoginCredential credential) {
    User user = customerUserService.getUserByEmail(credential.getEmail()).orElse(null);

    if (user == null) {
      logger.info("Login failed: user %s does not exists.".formatted(credential.getEmail()));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND,
        "User with provided credentials cannot login."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    if (!encoder.matches(credential.getPassword(), user.getPassword())) {
      logger.info("Login failed: wrong password.");
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND,
        "User with provided credentials cannot login."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    user.setLastLoginAt(LocalDateTime.now());
    user = customerUserService.update(user);

    logger.info("Login successful: %s has logged in.".formatted(credential.getEmail()));

    UserResponseDto response = mapper.map(user, UserResponseDto.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
    * Method used to create new user.
    *
    * @param payload user data to store into the database. See
    *     {@link UserCreationDto}.
    * @return Created user object.
   */
  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody UserCreationDto payload) {
    User checkIfExists = customerUserService.getUserByEmail(payload.getEmail()).orElse(null);

    if (checkIfExists != null) {
      logger.warn("User with email: %s already exists".formatted(payload.getEmail()));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.CONFLICT,
        "User with provided email already exists."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    User newUser = mapper.map(payload, User.class);
    newUser.setPassword(Utility.bcrypt(payload.getPassword()));
    newUser = customerUserService.createUser(newUser);

    UserResponseDto response = mapper.map(newUser, UserResponseDto.class);
    logger.info("User with id %s has been created.".formatted(newUser.getId()));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Update existing user.
   *
   * @param id id of the user that needs to be updated.
   * @param payload New user data.
   *     See {@link UserUpdateDto}
   * @return Updated user data.
   */
  @PatchMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable Long id,
    @RequestBody UserUpdateDto payload
  ) {
    User existing = customerUserService.getUserById(id).orElse(null);

    if (existing == null) {
      logger.warn("User with id %s does not exist. Cannot update it.".formatted(id));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST,
        "User with provided ID does not exist."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    existing = payload.overwrite(existing);

    existing = customerUserService.update(existing);
    UserResponseDto response = mapper.map(existing, UserResponseDto.class);

    logger.info("User with id %s has been updated".formatted(id));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Method to delete user data from the database.
   *
   * @param id ID of the target user.
   * @return Result of the operation.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@NotNull @PathVariable long id) {
    User existing = customerUserService.getUserById(id).orElse(null);

    if (existing == null) {
      logger.warn("User with id %s does not exist. Cannot delete it,".formatted(id));
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST,
        "User with provided ID does not exist."
      );
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    customerUserService.delete(existing);

    logger.info("User with id %s has been successfully removed".formatted(id));
    return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
  }
}
