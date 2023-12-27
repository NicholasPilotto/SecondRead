package com.nicholaspilotto.authenticationservice.controllers;

import com.nicholaspilotto.authenticationservice.constants.ProjectConstants;
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
 * Controller class used to manage all {@link AuthenticationResponse}s.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;
  private final JwtService jwtService;

  /**
   * Initializes an instance of {@link AuthController}.
   *
   * @param authService {@link AuthService} object reference.
   * @param jwtService {@link JwtService} object reference.
   */
  @Autowired
  public AuthController(AuthService authService, JwtService jwtService) {
    this.authService = authService;
    this.jwtService = jwtService;
  }

  /**
   * Creates a new reference of type {@link  AuthenticationResponse}.
   *
   * @param user {@link UserVO} from which to build the {@link  AuthenticationResponse}.
   *
   * @return {@link AuthenticationResponse} with {@link UserVO} ACCESS and REFRESH {@code JWT}.
   */
  private AuthenticationResponse createAuthenticationResponse(UserVO user) {
    String accessToken = jwtService.generateToken(
      user.getId().toString(),
      user.getRole(),
      ProjectConstants.TokenType.ACCESS
    );

    String refreshToken = jwtService.generateToken(
      user.getId().toString(),
      user.getRole(),
      ProjectConstants.TokenType.REFRESH
    );

    return new AuthenticationResponse(accessToken, refreshToken);
  }

  /**
   * Register a new{@link UserVO} into the system.
   * @param payload new {@link  UserVO} data.
   *
   * @return if the registration was successful, a new instance of {@link AuthenticationResponse},
   * otherwise, {@code BAD_REQUEST} status.
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest payload) {
    UserVO user = authService.register(payload);

    if (user == null) {
      return new ResponseEntity<>("Cannot register this user.", HttpStatus.BAD_REQUEST);
    }

    AuthenticationResponse response = createAuthenticationResponse(user);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Login an existing {@link UserVO} into the system.
   *
   * @param payload {@link UserVO} login credentials.
   * @return if the login was successful, a new instance of {@link AuthenticationResponse},
   * otherwise, {@code BAD_REQUEST} status.
   */
  @GetMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest payload) {
    UserVO user = authService.login(payload);

    if (user == null) {
      return new ResponseEntity<>("Cannot login this user.", HttpStatus.BAD_REQUEST);
    }

    AuthenticationResponse response = createAuthenticationResponse(user);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get {@link  UserVO} data from a {@code JWT} token.
   *
   * @param token {@code JWT} token used to get {@link UserVO} data.
   * @return {@link UserVO} data got from {@code token}.
   */
  @GetMapping("/whoami")
  public ResponseEntity<?> whoami(@NotNull @RequestHeader("Authorization") String token) {
    token = StringUtils.remove(token, ProjectConstants.HttpHeader.BEARER_HEADER);
    if (jwtService.isTokenExpired(token)) {
      return new ResponseEntity<>("Token is expired", HttpStatus.BAD_REQUEST);
    }

    Claims claims = jwtService.getClaims(token);
    Long userId = Long.parseLong(claims.get(ProjectConstants.JwtClaims.ID, String.class));

    UserVO user = authService.getUserById(userId);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
