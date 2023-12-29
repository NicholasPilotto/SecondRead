package com.nicholaspilotto.userservice.models.dtos.user;

import com.nicholaspilotto.userservice.models.entities.User;
import java.util.Date;

/**
 * Represents the model used to update User data.
 */
public class UserUpdateDto {
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;

  public UserUpdateDto() { }

  /**
   * Creates a new instance of {@link UserUpdateDto}.
   *
   * @param firstName user first name.
   * @param lastName user last name.
   * @param birthDate user birthdate.
   * @param phoneNumber user phone number.
   * @param email user email address.
   */
  public UserUpdateDto(
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
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

  /**
   * Overwrite existing User data with new data.
   *
   * @param existing User already stored into database.
   *
   * @return Updated User data.
   */
  public User overwrite(User existing) {
    if (firstName != null) {
      existing.setFirstName(firstName);
    }

    if (lastName != null) {
      existing.setLastName(lastName);
    }

    if (birthDate != null) {
      existing.setBirthDate(birthDate);
    }

    if (phoneNumber != null) {
      existing.setPhoneNumber(phoneNumber);
    }

    if (email != null) {
      existing.setEmail(email);
    }

    return existing;
  }
}
