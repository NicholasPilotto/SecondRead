package com.nicholaspilotto.authenticationservice.services;

import com.nicholaspilotto.authenticationservice.models.AuthenticationResponse;
import com.nicholaspilotto.authenticationservice.models.RegisterRequest;
import com.nicholaspilotto.authenticationservice.models.UserVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
  private final RestTemplate restTemplate;
  private final JwtService jwtService;

  @Autowired
  public AuthService(RestTemplate restTemplate, JwtService jwtService) {
    this.restTemplate = restTemplate;
    this.jwtService = jwtService;
  }

  public AuthenticationResponse register(RegisterRequest payload) {
    payload.setPassword(BCrypt.hashpw(payload.getPassword(), BCrypt.gensalt()));
    UserVO user = restTemplate.postForObject("http://user-service/user", payload, UserVO.class);

    if (user == null) {
      return null;
    }

    String accessToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "ACCESS");
    String refreshToken = jwtService.generateToken(user.getId().toString(), user.getRole(), "REFRESH");

    return new AuthenticationResponse(accessToken, refreshToken);
  }
}
