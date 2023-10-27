package com.nicholaspilotto.userservice.services.interfaces;

import com.nicholaspilotto.userservice.models.entities.User;

/**
 * Represents the interface class used to interact with generic user data.
 */
public interface UserService {
  public abstract User getUser(Long id);
}
