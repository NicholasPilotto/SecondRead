package com.nicholaspilotto.authenticationservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Represents the role that a User can have.
 */
@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum Role {
  /**
   * The user is an admin of the system.
   */
  ADMIN,
  /**
   * The user is customer.
   */
  CUSTOMER,
  /**
   * The user an owner.
   */
  OWNER,
  /**
   * The user an employee.
   */
  EMPLOYEE,
  ;

  @Override
  public String toString() {
    return this.name();
  }

  public String ordinalString() {
    return "%s".formatted(this.ordinal());
  }
}