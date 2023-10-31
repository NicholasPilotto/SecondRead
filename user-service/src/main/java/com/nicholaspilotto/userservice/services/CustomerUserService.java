package com.nicholaspilotto.userservice.services;

import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.repositories.UserRepository;
import com.nicholaspilotto.userservice.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Represents the customer user service class used to
 * interact with customer user data.
 */
@Service
public class CustomerUserService implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

}
