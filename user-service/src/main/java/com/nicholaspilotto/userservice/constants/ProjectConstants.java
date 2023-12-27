package com.nicholaspilotto.userservice.constants;

/**
 * Contains all constant values used in all project.
 */
public class ProjectConstants {
  /**
   * Contains constants that represent {@code HTTP} headers.
   */
  public static class HTTP_HEADERS {
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
  public static class CLAIMS {
    /**
     * {@code role} claims.
     */
    public static final String ROLE = "role";
  }
}
