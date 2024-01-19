package com.nicholaspilotto.userservice.services.interfaces;

import com.nicholaspilotto.userservice.models.entities.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Represents the interface class used to interact with generic user data.
 */
public interface UserService {
  /**
   * Get the list of all users.
   *
   * @param pageable {@link Page} data.
   * @param specification {@link Specification} object.
   *
   * @return List containing all users.
   */
  public abstract Page<User> getAllUsers(Pageable pageable, Specification<User> specification);

  /**
   * Get a specific user by its id.
   *
   * @param id id of the target user.
   *
   * @return {@code Optional<User> } with {@code id}.
   */
  public abstract Optional<User> getUserById(Long id);

  /**
   * Get a specific user by its email address.
   *
   * @param email email of the target user.
   *
   * @return {@code Optional<User>} with {@code email}.
   */
  public abstract Optional<User> getUserByEmail(String email);

  /**
   * Create new user.
   *
   * @param user user to store into the database.
   *
   * @return Created user object.
   */
  public abstract User createUser(User user);

  /**
   * Calculate the count of the users stored into
   * the database.
   *
   * @param specification {@link Specification} object.
   *
   * @return Number of user store into the database.
   */
  public abstract long count(Specification<User> specification);

  /**
   * Update user data.
   *
   * @param existing User to update.
   *
   * @return Updated User data.
   */
  public abstract User update(User existing);

  /**
   * Delete user data.
   *
   * @param existing User to delete.
   */
  public abstract void delete(User existing);
}
