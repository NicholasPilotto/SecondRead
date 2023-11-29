package com.nicholaspilotto.authenticationservice.models;

public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String Email;
  private String password;

  public RegisterRequest(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    Email = email;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "RegisterRequest{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", Email='" + Email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
