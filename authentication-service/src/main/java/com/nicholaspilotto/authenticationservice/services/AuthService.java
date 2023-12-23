package com.nicholaspilotto.authenticationservice.services;

import com.nicholaspilotto.authenticationservice.models.AuthenticationResponse;
import com.nicholaspilotto.authenticationservice.models.LoginRequest;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.models.UserVO;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
  private final RestTemplate restTemplate;

  @Autowired
  public AuthService(RestTemplate restTemplate, JwtService jwtService) {
    this.restTemplate = restTemplate;
  }

  public UserVO register(RegisterRequest payload) {
    return restTemplate.postForObject("http://user-service/user", payload, UserVO.class);
  }

  public UserVO login(LoginRequest payload) {
    return restTemplate.postForObject("http://user-service/user/login", payload, UserVO.class);
  }

  public  UserVO getUserById(Long id) {
    return restTemplate.getForObject("http://user-service/user/{id}", UserVO.class, id);
  }
}
