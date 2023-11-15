package com.nicholaspilotto.securityservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceFeign implements UserDetailsService {
  private final UserService userService;

  @Autowired
  public UserDetailsServiceFeign(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = userService.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return user;
  }
}
