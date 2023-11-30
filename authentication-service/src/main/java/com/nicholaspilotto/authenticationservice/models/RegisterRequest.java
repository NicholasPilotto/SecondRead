package com.nicholaspilotto.authenticationservice.models;

import java.util.Date;

public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String email;
  private Date birthDate;
  private String phoneNumber;
  private String password;
  private Role role;

  public RegisterRequest(
    String firstName,
    String lastName,
    String email,
    Date birthDate,
    String phoneNumber,
    String password,
    Role role
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.role = role;
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
    return email;
  }

  public void setEmail(String email) {
    email = email;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "RegisterRequest{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", birthDate=" + birthDate +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      '}';
  }
}
