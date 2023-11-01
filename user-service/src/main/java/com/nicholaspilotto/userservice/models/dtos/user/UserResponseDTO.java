package com.nicholaspilotto.userservice.models.dtos.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

/**
 * Represent user data model used as response.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;

  public UserResponseDTO() { }

  public UserResponseDTO(
    Long id,
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "UserResponseDto{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", birthDate='" + birthDate + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
