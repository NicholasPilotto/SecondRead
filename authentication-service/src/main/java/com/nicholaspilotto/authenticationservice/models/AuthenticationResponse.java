package com.nicholaspilotto.authenticationservice.models;

/**
 * Model used to return a successful authentication response.
 */
public class AuthenticationResponse {
  private String accessToken;
  private String refreshToken;

  /**
   * Creates a new instance of {@link AuthenticationResponse} object.
   *
   * @param accessToken token for authenticating and authorizing request.
   * @param refreshToken token for refreshing {@link #accessToken}.
   */
  public AuthenticationResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public String toString() {
    return "AuthenticationResponse{"
      + "accessToken='" + accessToken + '\''
      + ", refreshToken='" + refreshToken + '\''
      + '}';
  }
}
