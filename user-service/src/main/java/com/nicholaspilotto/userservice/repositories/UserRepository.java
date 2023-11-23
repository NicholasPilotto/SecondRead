package com.nicholaspilotto.userservice.repositories;

import com.nicholaspilotto.userservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Represents the repository class for the {@link com.nicholaspilotto.userservice.models.entities.User }
 */
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * Find {@link com.nicholaspilotto.userservice.models.entities.User} with a particular
   * {@code email}.
   *
   * @param email Email of the desired {@code User}.
   * @return {@code Optiona<User>} with particular {@code email}.
   */
  Optional<User> findByEmail(String email);

  /**
   * Find {@link User} with particular {@code email} and {@code password}.
   *
   * @param email Email of the desired {@code User}.
   * @param password Password of the desired {@code User}.
   * @return {@code Optional<User>} with {@code email} and {@code password}.
   */
  Optional<User> findByEmailAndPassword(String email, String password);
}
