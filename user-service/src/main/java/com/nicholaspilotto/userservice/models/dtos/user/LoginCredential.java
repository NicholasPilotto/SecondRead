package com.nicholaspilotto.userservice.models.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginCredential {
  @Email
  private String email;

  @Size(min = 4, max = 15, message = "Password length must be between 4 and 15")
  private String password;

  public LoginCredential() {
  }

  public LoginCredential(String email, String password) {
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
    return "LoginCredential{" +
      "email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
