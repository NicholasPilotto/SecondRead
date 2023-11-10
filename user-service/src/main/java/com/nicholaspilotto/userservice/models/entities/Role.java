package com.nicholaspilotto.userservice.models.entities;

/**
 * Represents the role that a User can have.
 */
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
}