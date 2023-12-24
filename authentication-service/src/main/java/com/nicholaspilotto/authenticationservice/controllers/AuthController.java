package com.nicholaspilotto.authenticationservice.controllers;

import com.nicholaspilotto.authenticationservice.models.AuthenticationResponse;
import com.nicholaspilotto.authenticationservice.models.LoginRequest;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.models.UserVO;
import com.nicholaspilotto.authenticationservice.services.AuthService;
import com.nicholaspilotto.authenticationservice.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class used to manage all Authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;
  private final JwtService jwtService;

  /**
   * Initializes an instance of {@code AuthController}.
   *
   * @param authService {@code AuthService} object reference.
   * @param jwtService {@code JwtService} object reference.
   */
  @Autowired
  public AuthController(AuthService authService, JwtService jwtService) {
    this.authService = authService;
    this.jwtService = jwtService;
  }

  /**
   * Register a new{@code User} into the system.
   * @param payload new {@code User} data.
   *
   * @return if the registration was successful, a new instance of {@code AuthenticationResponse},
   * otherwise, {@code BAD_REQUEST} status.
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest payload) {
    UserVO user = authService.register(payload);

    if (user == null) {
      return new ResponseEntity<>("Cannot register this user.", HttpStatus.BAD_REQUEST);
    }

    String accessToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "ACCESS");
    String refreshToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "REFRESH");

    AuthenticationResponse response = new AuthenticationResponse(accessToken, refreshToken);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Login an existing {@code User} into the system.
   *
   * @param payload {@code User} login credentials.
   * @return if the login was successful, a new instance of {@code AuthenticationResponse},
   * otherwise, {@code BAD_REQUEST} status.
   */
  @GetMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest payload) {
    UserVO user = authService.login(payload);

    if (user == null) {
      return new ResponseEntity<>("Cannot login this user.", HttpStatus.BAD_REQUEST);
    }

    String accessToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "ACCESS");
    String refreshToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "REFRESH");

    AuthenticationResponse response = new AuthenticationResponse(accessToken, refreshToken);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get {@code User} data from a {@code JWT} token.
   *
   * @param token {@code JWT} token used to get {@code User} data.
   * @return {@code User} data got from {@code token}.
   */
  @GetMapping("/whoami")
  public ResponseEntity<?> whoami(@NotNull @RequestHeader("Authorization") String token) {
    token = StringUtils.remove(token, "Bearer ");
    if (jwtService.isTokenExpired(token)) {
      return new ResponseEntity<>("Token is expired", HttpStatus.BAD_REQUEST);
    }

    Claims claims = jwtService.getClaims(token);
    Long userId = Long.parseLong(claims.get("id", String.class));

    UserVO user = authService.getUserById(userId);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
