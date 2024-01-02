package com.nicholaspilotto.authenticationservice.services;

import com.nicholaspilotto.authenticationservice.constants.ProjectConstants;
import com.nicholaspilotto.authenticationservice.models.LoginRequest;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.models.UserVo;
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
   * Register a new {@link UserVo} into the system.
   *
   * @param payload {@link UserVo} data used to register new user.
   * @return created {@link UserVo} data.
   */
  public UserVo register(RegisterRequest payload) {
    return restTemplate.postForObject(ProjectConstants.UrlServiceRequests.USER_SERVICE_USER, payload, UserVo.class);
  }

  /**
   * Login an existing {@link UserVo} into the system.
   *
   * @param payload {@link UserVo} login credentials.
   * @return {@link UserVo} if the credentials match, otherwise, {@code null}.
   */
  public UserVo login(LoginRequest payload) {
    return restTemplate.postForObject(
      ProjectConstants.UrlServiceRequests.USER_SERVICE_USER + "/login",
      payload,
      UserVo.class
    );
  }

  /**
   * Get an existing {@link UserVo} by its identifier.
   * @param id identifier of the {@link UserVo}.
   * @return {@link UserVo} with {@code id} as identifier.
   */
  public UserVo getUserById(Long id) {
    return restTemplate.getForObject(
      ProjectConstants.UrlServiceRequests.USER_SERVICE_USER + "/{id}",
      UserVo.class,
      id
    );
  }
}
