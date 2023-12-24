package com.nicholaspilotto.authenticationservice.services;

import com.nicholaspilotto.authenticationservice.constants.UrlServiceRequests;
import com.nicholaspilotto.authenticationservice.models.LoginRequest;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.models.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class that manages the authentication requests.
 */
@Service
public class AuthService {
  private final RestTemplate restTemplate;

  /**
   * Initializes {@code AuthService} class.
   *
   * @param restTemplate {@code RestTemplate} class reference.
   * @param jwtService {@code JwtService} class reference.
   */
  @Autowired
  public AuthService(RestTemplate restTemplate, JwtService jwtService) {
    this.restTemplate = restTemplate;
  }

  /**
   * Register a new {@code User} into the system.
   *
   * @param payload {@code user} data used to register new user.
   * @return created {@code User} data.
   */
  public UserVO register(RegisterRequest payload) {
    return restTemplate.postForObject(UrlServiceRequests.USER_SERVICE_USER, payload, UserVO.class);
  }

  /**
   * Login an existing {@code User} into the system.
   *
   * @param payload {@code User} login credentials.
   * @return {@code User} if the credentials match, otherwise, {@code null}.
   */
  public UserVO login(LoginRequest payload) {
    return restTemplate.postForObject(UrlServiceRequests.USER_SERVICE_USER + "/login", payload, UserVO.class);
  }

  /**
   * Get an existing {@code User} by its identifier.
   * @param id identifier of the {@code User}.
   * @return {@code User} with {@code id} as identifier.
   */
  public  UserVO getUserById(Long id) {
    return restTemplate.getForObject(UrlServiceRequests.USER_SERVICE_USER + "/{id}", UserVO.class, id);
  }
}
