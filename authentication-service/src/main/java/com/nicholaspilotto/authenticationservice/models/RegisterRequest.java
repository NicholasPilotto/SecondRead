package com.nicholaspilotto.authenticationservice.models;

import java.util.Date;

/**
 * Model used to register a new {@code User} into the system.
 */
public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String mail;
  private Date birthDate;
  private String phoneNumber;
  private String password;
  private Role role;

  /**
   * Creates a new instance of {@link RegisterRequest} object.
   *
   * @param firstName user first name.
   * @param lastName user last name.
   * @param email user email address.
   * @param birthDate user birthdate-
   * @param phoneNumber user phone number.
   * @param password user password.
   * @param role user {@link Role}.
   */
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
    this.mail = email;
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

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    mail = mail;
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
    return "RegisterRequest{"
      + "firstName='" + firstName + '\''
      + ", lastName='" + lastName + '\''
      + ", email='" + mail + '\''
      + ", birthDate=" + birthDate
      + ", phoneNumber='" + phoneNumber + '\''
      + ", password='" + password + '\''
      + ", role=" + role
      + '}';
  }
}
