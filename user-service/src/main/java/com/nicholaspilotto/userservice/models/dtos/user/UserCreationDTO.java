package com.nicholaspilotto.userservice.models.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Represents user data used to create a new user object.
 */
public class UserCreationDTO {
  @NotEmpty
  @Size(min = 2, max = 200, message = "First name length must be between 2 and 200")
  private String firstName;
  @NotEmpty
  @Size(min = 2, max = 200, message = "Last name length must be between 2 and 200")
  private String lastName;
  @NotEmpty
  private Date birthDate;
  @Size(min = 5, max = 15)
  private String phoneNumber;
  @Email(flags = { Pattern.Flag.UNICODE_CASE })
  private String email;
  @Size(min = 4, max = 15, message = "Password length must be between 4 and 15")
  private String password;

  public UserCreationDTO() { }

  public UserCreationDTO(
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email,
    String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
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
    return "UserCreationDTO{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", birthDate='" + birthDate + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
