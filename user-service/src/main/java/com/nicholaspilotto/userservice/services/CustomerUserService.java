package com.nicholaspilotto.userservice.services;

import com.nicholaspilotto.userservice.models.entities.User;
import com.nicholaspilotto.userservice.repositories.UserRepository;
import com.nicholaspilotto.userservice.services.interfaces.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * Represents the customer user service class used to
 * interact with customer user data.
 */
@Service
public class CustomerUserService implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Page<User> getAllUsers(Pageable pageable, Specification<User> specification) {
    return userRepository.findAll(specification, pageable);
  }

  @Override
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public long count(Specification<User> specification) {
    return userRepository.count(specification);
  }

  @Override
  public User update(User existing) {
    return userRepository.save(existing);
  }

  @Override
  public void delete(User existing) {
    userRepository.delete(existing);
  }
}
