package com.nicholaspilotto.userservice.services;

import com.nicholaspilotto.userservice.services.interfaces.UserService;
import org.springframework.stereotype.Service;

/**
 * Represents the customer user service class used to
 * interact with customer user data.
 */
@Service
public class CustomerUserService implements UserService {
  @Override
  public String getUser() {
    return "Kylan Gentry";
  }
}
