package com.nicholaspilotto.apigateway.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements UserDetails {
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;
  private String password;
  Role role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public User() { }

  /**
   * Class constructor
   * @param id id of the user.
   * @param firstName first name of the user.
   * @param lastName last name of the user.
   * @param birthDate birthdate of the user.
   * @param email email of the user.
   * @param phoneNumber phone number of the user.
   * @param password password of the user.
   * @param role role of the user.
   * @param createdAt date of creation.
   * @param updatedAt last update date.
   */
  public User(
    Long id,
    String firstName,
    String lastName,
    Date birthDate,
    String email,
    String phoneNumber,
    String password,
    Role role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  /**
   * Gets the id of the user.
   * @return id of the user.
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the user first name.
   * @return user first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name to the user.
   * @param firstName first name to set to the user.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Get the user last name.
   * @return user last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name to the user.
   * @param lastName last name to set to the user.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the user birthdate.
   * @return user birthdate.
   */
  public Date getBirthDate() {
    return birthDate;
  }

  /**
   * Sets the birthdate to the user.
   * @param birthDate birthdate to set to the user.
   */
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Gets the user phone number.
   * @return phone number of the user.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number to the user.
   * @param phoneNumber phone number to set to the user.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the user email.
   * @return email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email to the user.
   * @param email email of the user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the user password.
   * @return password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password to the user.
   * @param password password of the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the role of the user.
   * @return role of the user.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets the role to the user.
   * @param role role of the user.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Gets the creation date of the user.
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the creation date of the user.
   * @param createdAt creation date of the user.
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Sets the last update date of the user.
   */
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  /**
   * Sets the last update date of the user.
   * @param updatedAt last update date of the user.
   */
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Check if two object equals.
   * @param o object to check if is equals to current object.
   * @return true if the two object are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;

    return this.hashCode() == o.hashCode();
  }

  /**
   * Generate current user hash code.
   * @return the hash code of the current user object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, birthDate, phoneNumber, email, password, role, createdAt, updatedAt);
  }

  /**
   * Transform current user object into string.
   * @return string representing current user object.
   */
  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", birthDate=" + birthDate +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }
}
