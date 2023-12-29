package com.nicholaspilotto.userservice.models.dtos.user;

import com.nicholaspilotto.userservice.models.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 * Represents user data used to create a new user object.
 */
public class UserCreationDto {
  @NotEmpty
  @Size(min = 2, max = 200, message = "First name length must be between 2 and 200")
  private String firstName;
  @NotEmpty
  @Size(min = 2, max = 200, message = "Last name length must be between 2 and 200")
  private String lastName;
  @NotNull
  private Date birthDate;
  @Size(min = 5, max = 15)
  private String phoneNumber;
  @Email(flags = { Pattern.Flag.UNICODE_CASE })
  private String email;
  @Size(min = 4, max = 15, message = "Password length must be between 4 and 15")
  private String password;

  @NotNull
  private Role role;

  public UserCreationDto() { }

  /**
   * Creates a new instance of {@link UserCreationDto}.
   *
   * @param firstName user first name.
   * @param lastName user last name.
   * @param birthDate user birthday.
   * @param phoneNumber user phone number.
   * @param email user email address.
   * @param password user password.
   * @param role user role.
   */
  public UserCreationDto(
    String firstName,
    String lastName,
    Date birthDate,
    String phoneNumber,
    String email,
    String password,
    Role role
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserCreationDTO{"
      + "firstName='" + firstName + '\''
      + ", lastName='" + lastName + '\''
      + ", birthDate=" + birthDate
      + ", phoneNumber='" + phoneNumber + '\''
      + ", email='" + email + '\''
      + ", password='" + password + '\''
      + ", role=" + role
      + '}';
  }
}
