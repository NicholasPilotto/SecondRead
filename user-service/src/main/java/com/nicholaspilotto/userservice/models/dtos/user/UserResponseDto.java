package com.nicholaspilotto.userservice.models.dtos.user;

import com.nicholaspilotto.userservice.models.entities.Role;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represent user data model used as response.
 */
public class UserResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;
  private Role role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserResponseDto() { }

  /**
   * Creates a new instance of {@link UserResponseDto}.
   *
   * @param id user id.
   * @param firstName user first name.
   * @param lastName user last name.
   * @param birthDate user birthday.
   * @param phoneNumber user phone number.
   * @param email user email.
   * @param role user role.
   * @param createdAt user account date creation.
   * @param updatedAt date of the last update of the account.
   */
  public UserResponseDto(
    Long id,
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email,
    Role role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "UserResponseDTO{"
      + "id=" + id
      + ", firstName='" + firstName + '\''
      + ", lastName='" + lastName + '\''
      + ", birthDate=" + birthDate
      + ", phoneNumber='" + phoneNumber + '\''
      + ", email='" + email + '\''
      + ", role=" + role
      + ", createdAt=" + createdAt
      + ", updatedAt=" + updatedAt
      + '}';
  }
}
