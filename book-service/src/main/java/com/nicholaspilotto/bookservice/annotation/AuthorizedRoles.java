package com.nicholaspilotto.bookservice.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.nicholaspilotto.bookservice.models.roles.Role;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@link Role} admin decorator.
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