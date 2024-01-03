package com.nicholaspilotto.authenticationservice.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a login request object.
 */
public class LoginRequest {
  @Email
  private String email;

  @NotNull
  @Size(min = 32, max = 72)
  private String password;

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginRequest{"
      + "email='" + email + '\''
      + ", password='" + password + '\''
      + '}';
  }
}
