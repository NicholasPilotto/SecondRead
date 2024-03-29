package com.nicholaspilotto.bookservice.models.vos;

import com.nicholaspilotto.bookservice.models.roles.Role;
import java.util.Date;
import java.util.Objects;

/**
 * {@code Value Object} class for {@code User} model.
 */
public class UserVo {
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;
  private Role role;

  private Date lastLoginAt;
  private Date createdAt;
  private Date updatedAt;

  public UserVo() { }

  /**
   * Creates a new instance of {@link UserVo} object.
   *
   * @param id user identifier.
   * @param firstName user first name.
   * @param lastName user last name.
   * @param birthDate user birthdate.
   * @param phoneNumber user phone number.
   * @param email user email address.
   * @param role user {@link Role}.
   * @param lastLoginAt last user login.
   * @param createdAt user account creation date.
   * @param updatedAt last user account update date.
   */
  public UserVo(
    Long id,
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email,
    Role role,
    Date lastLoginAt,
    Date createdAt,
    Date updatedAt
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.role = role;
    this.lastLoginAt = lastLoginAt;
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

  public Date getLastLoginAt() {
    return lastLoginAt;
  }

  public void setLastLoginAt(Date lastLoginAt) {
    this.lastLoginAt = lastLoginAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserVo user = (UserVo) o;
    return Objects.equals(id, user.id) && Objects.equals(
      firstName,
      user.firstName
    ) && Objects.equals(lastName, user.lastName) && Objects.equals(
      birthDate,
      user.birthDate
    ) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(
      email,
      user.email
    ) && role == user.role && Objects.equals(createdAt, user.createdAt)
      && Objects.equals(lastLoginAt, user.lastLoginAt) && Objects.equals(
      updatedAt,
      user.updatedAt
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      firstName,
      lastName,
      birthDate,
      phoneNumber,
      email,
      role,
      lastLoginAt,
      createdAt,
      updatedAt
    );
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
      + ", lastLoginAt=" + lastLoginAt
      + ", createdAt=" + createdAt
      + ", updatedAt=" + updatedAt
      + '}';
  }
}