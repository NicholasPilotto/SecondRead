package com.nicholaspilotto.authenticationservice.services;

import com.nicholaspilotto.authenticationservice.constants.ProjectConstants;
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
   * Initializes {@link AuthService} class.
   *
   * @param restTemplate {@link RestTemplate} class reference.
   * @param jwtService {@link JwtService} class reference.
   */
  @Autowired
  public AuthService(RestTemplate restTemplate, JwtService jwtService) {
    this.restTemplate = restTemplate;
  }

  /**
   * Register a new {@link UserVO} into the system.
   *
   * @param payload {@link UserVO} data used to register new user.
   * @return created {@link UserVO} data.
   */
  public UserVO register(RegisterRequest payload) {
    return restTemplate.postForObject(ProjectConstants.UrlServiceRequests.USER_SERVICE_USER, payload, UserVO.class);
  }

  /**
   * Login an existing {@link UserVO} into the system.
   *
   * @param payload {@link UserVO} login credentials.
   * @return {@link UserVO} if the credentials match, otherwise, {@code null}.
   */
  public UserVO login(LoginRequest payload) {
    return restTemplate.postForObject(
      ProjectConstants.UrlServiceRequests.USER_SERVICE_USER + "/login",
      payload,
      UserVO.class
    );
  }

  /**
   * Get an existing {@link UserVO} by its identifier.
   * @param id identifier of the {@link UserVO}.
   * @return {@link UserVO} with {@code id} as identifier.
   */
  public  UserVO getUserById(Long id) {
    return restTemplate.getForObject(
      ProjectConstants.UrlServiceRequests.USER_SERVICE_USER + "/{id}",
      UserVO.class,
      id
    );
  }
}
