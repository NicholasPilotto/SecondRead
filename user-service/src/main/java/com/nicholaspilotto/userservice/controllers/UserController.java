package com.nicholaspilotto.userservice.controllers;

import com.nicholaspilotto.userservice.annotations.RoleAdmin;
import com.nicholaspilotto.userservice.models.dtos.user.LoginCredential;
import com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserResponseDTO;
import com.nicholaspilotto.userservice.models.dtos.user.UserUpdateDTO;
import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.services.CustomerUserService;
import com.nicholaspilotto.userservice.utilities.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
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
  @RoleAdmin
  public ResponseEntity<?> getAllUsers(final Pageable pageable) {
    List<User> users = customerUserService.getAllUsers(pageable).getContent();
    List<UserResponseDTO> response = Arrays.stream(mapper.map(users, UserResponseDTO[].class)).toList();
    logger.info("Users has been requested.");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get the number of user store into the database.
   *
   * @return Number of user store into the database.
   */
   @GetMapping("/count")
   @RoleAdmin
   public ResponseEntity<Long> count() {
      Long count = customerUserService.count();
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
      return new ResponseEntity<>("User with provided id does not exist.", HttpStatus.NOT_FOUND);
    }

    logger.info("User with id %s has been found.".formatted(id));
    UserResponseDTO response = mapper.map(user, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<?> getByEmail(@PathVariable @Email String email) {
    User user = customerUserService.getUserByEmail(email).orElse(null);

    if (user == null) {
      return new ResponseEntity<>("User with provided email does not exist", HttpStatus.NOT_FOUND);
    }

    logger.info("User with email %s has been found".formatted(email));
    UserResponseDTO responseDTO = mapper.map(user, UserResponseDTO.class);

    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @NotNull LoginCredential credential) {
    User user = customerUserService.getUserByEmail(credential.getEmail()).orElse(null);

    if (user == null) {
      logger.info("Login failed: user %s does not exists.".formatted(credential.getEmail()));
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    if (!encoder.matches(credential.getPassword(), user.getPassword())) {
      logger.info("Login failed: wrong password.");
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    logger.info("Login successful: %s has logged in.".formatted(credential.getEmail()));

    UserResponseDTO response = mapper.map(user, UserResponseDTO.class);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Method used to create new user.
   *
   * @param payload user data to store into the database.
   * See {@link com.nicholaspilotto.userservice.models.dtos.user.UserCreationDTO}
   * @return Created user object.
   */
  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody UserCreationDTO payload) {
    User checkIfExists = customerUserService.getUserByEmail(payload.getEmail()).orElse(null);

    if (checkIfExists != null) {
      logger.warn("User with email: %s already exists".formatted(payload.getEmail()));
      return new ResponseEntity<>("User with this email already exist", HttpStatus.CONFLICT);
    }

    User newUser = mapper.map(payload, User.class);
    newUser.setPassword(Utility.bcrypt(payload.getPassword()));
    newUser = customerUserService.createUser(newUser);

    UserResponseDTO response = mapper.map(newUser, UserResponseDTO.class);
    logger.info("User with id %s has been created.".formatted(newUser.getId()));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Update existing user.
   *
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
    User existing = customerUserService.getUserById(id).orElse(null);

    if (existing == null) {
      logger.warn("User with id %s does not exist. Cannot update it.".formatted(id));
      return new ResponseEntity<>(
        "User with provided ID does not exist",
        HttpStatus.BAD_REQUEST
      );
    }

    existing = payload.overwrite(existing);

    existing = customerUserService.update(existing);
    UserResponseDTO response = mapper.map(existing, UserResponseDTO.class);

    logger.info("User with id %s has been updated".formatted(id));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Generate fake user data and store the into the database.
   *
   * @param number number of fake user to generate.
   * @return The list of fake user generated.
   */
  @PostMapping("/generate-fake-data/{number}")
  public ResponseEntity<?> generateFakeData(@PathVariable int number) {
    List<User> fakeUsers = Utility.generateFakeUsers(number);
    List<User> result = new ArrayList<>();

    for (User fakeUser : fakeUsers) {
      result.add(customerUserService.createUser(fakeUser));
    }

    logger.info("%s fake users have been created".formatted(number));
    return new ResponseEntity<>(result, HttpStatus.OK);
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
      return new ResponseEntity<>("User with provided ID does not exist", HttpStatus.BAD_REQUEST);
    }

    logger.info("User with id %s has been successfully removed".formatted(id));
    return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
  }
}
