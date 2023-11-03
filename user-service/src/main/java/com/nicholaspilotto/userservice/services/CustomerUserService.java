package com.nicholaspilotto.userservice.services;

import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.repositories.UserRepository;
import com.nicholaspilotto.userservice.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Represents the customer user service class used to
 * interact with customer user data.
 */
@Service
public class CustomerUserService implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Page<User> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }
  @Override
  public Optional<User> getUser(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public long count() {
    return userRepository.count();
  }

  @Override
  public User update(User existing) {
    return userRepository.save(existing);
  }
}
