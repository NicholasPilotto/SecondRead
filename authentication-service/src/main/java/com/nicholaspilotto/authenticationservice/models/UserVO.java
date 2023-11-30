package com.nicholaspilotto.authenticationservice.models;

import java.util.Date;
import java.util.Objects;

public class UserVO {
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phoneNumber;
  private String email;
  private String password;
  private Role role;

  public UserVO(Long id, String email, String password, Role role) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserVO userVO = (UserVO) o;
    return Objects.equals(id, userVO.id) && Objects.equals(
      email,
      userVO.email
    ) && Objects.equals(password, userVO.password) && Objects.equals(role, userVO.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, role);
  }

  @Override
  public String toString() {
    return "UserVO{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", role='" + role + '\'' +
      '}';
  }
}
