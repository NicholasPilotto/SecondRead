package com.nicholaspilotto.userservice.services.interfaces;

import com.nicholaspilotto.userservice.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Represents the interface class used to interact with generic user data.
 */
public interface UserService {
  /**
   * Get the list of all users.
   * @param pageable Page data.
   * @return List containing all users.
   */
  public abstract Page<User> getAllUsers(Pageable pageable);

  /**
   * Get a specific user by its id.
   * @param id id of the target user.
   * @return {@code Optional<User> } with {@code id}.
   */
  public abstract Optional<User> getUser(Long id);

  /**
   * Create new user.
   * @param user user to store into the database.
   * @return Created user object.
   */
  public abstract User createUser(User user);

  /**
   * Calculate the count of the users stored into
   * the database.
   * @return Number of user store into the database.
   */
  public abstract long count();

  /**
   * Update user data.
   * @param existing User to update.
   * @return Updated User data.
   */
  public abstract User update(User existing);
}
