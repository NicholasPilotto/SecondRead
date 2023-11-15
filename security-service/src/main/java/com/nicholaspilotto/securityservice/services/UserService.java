package com.nicholaspilotto.securityservice.services;

@FeignClient(name = "user-service")
public interface UserService {
  @GetMapping("user-service/user/email/{email}")
  public User findByEmail(@PathVariable String email);
}
