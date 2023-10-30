package com.nicholaspilotto.userservice.services.interfaces;

import com.nicholaspilotto.userservice.models.entities.User;

import java.util.List;

/**
 * Represents the interface class used to interact with generic user data.
 */
public interface UserService {
  /**
   * Get the list of all users.
   * @return List containing all users.
   */
  public abstract List<User> getAllUsers();

  /**
   * Get a specific user by its id.
   * @param id id of the target user.
   * @return User with {@code id}.
   */
  public abstract User getUser(Long id);
}
