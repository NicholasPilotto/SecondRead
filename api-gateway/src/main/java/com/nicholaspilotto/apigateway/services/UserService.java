package com.nicholaspilotto.apigateway.services;

import com.nicholaspilotto.apigateway.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserService {
  @GetMapping("user-service/user/email/{email}")
  public User findByEmail(@PathVariable String email);
}
