package com.nicholaspilotto.userservice.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.nicholaspilotto.userservice.models.entities.Role;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@link com.nicholaspilotto.userservice.models.entities.Role} admin decorator. Is used to make a route accessible
 *     only for AMIN {@link com.nicholaspilotto.userservice.models.entities.User}s.
 */
@Retention(RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AuthorizedRoles {
  /**
   * Authorized {@link Role}.
   *
   * @return The array of the authorized {@link Role}s.
   */
  Role[] authorized();
}
