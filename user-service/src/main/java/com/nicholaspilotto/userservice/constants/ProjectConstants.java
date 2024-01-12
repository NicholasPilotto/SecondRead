package com.nicholaspilotto.userservice.constants;

/**
 * Contains all constant values used in all project.
 */
public class ProjectConstants {
  /**
   * Contains constants that represent {@code HTTP} headers.
   */
  public static class HttpHeaders {
    /**
     * {@code HTTP} authorization header.
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * {@code Bearer} token prefix.
     */
    public static final String BEARER = "Bearer ";
  }

  /**
   * Contains all constants used to get values from {@code JWT} token claims.
   */
  public static class Claims {
    /**
     * {@code role} claims.
     */
    public static final String ROLE = "role";
  }

  /**
   * Project {@link String} constants.
   */
  public static class StringConstants {
    /**
     * Represents an empty {@link String}.
     */
    public static final String EMPTY = "";
  }
}
