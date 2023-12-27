package com.nicholaspilotto.authenticationservice.constants;

/**
 * Contains all constants used inside the project.
 */
public final class ProjectConstants {
  /**
   * Contains all {@code HTTP} header constants.
   */
  public static class HttpHeader {
    /**
     * Represents the {@code bearer} header.
     */
    public static final String BEARER_HEADER = "Bearer ";
  }

  /**
   * Represents the services urls.
   */
  public static final class UrlServiceRequests {
    public static final String USER_SERVICE_USER = "http://user-service/user/";
  }

  /**
   * Represents the type that a {@code JWT} can be.
   */
  public static final class TokenType {
    /**
     * Access token.
     */
    public static final String ACCESS = "ACCESS";

    /**
     * Refresh token.
     */
    public static final String REFRESH = "REFRESH";
  }

  /**
   * Represents the field contained into a {@code JWT} claim.
   */
  public static final class JwtClaims {
    /**
     * id field.
     */
    public static final String ID = "id";

    /**
     * Role field.
     */
    public static final String ROLE = "role";
  }
}
