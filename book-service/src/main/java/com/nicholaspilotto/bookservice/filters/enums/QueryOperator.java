package com.nicholaspilotto.bookservice.filters.enums;

/**
 * Represents the operator that a query can have.
 */
public enum QueryOperator {
  /**
   * Check if a values is greater that another.
   */
  GREATER_THAN,

  /**
   * Check if a value is less than another.
   */
  LESS_THAN,

  /**
   * Check if a value is equal to another.
   */
  EQUALS,

  /**
   * Check if a value is like another.
   */
  LIKE,

  /**
   * Check if a value is not equal to another.
   */
  NOT_EQ,

  /**
   * Check if a value is in a set of values.
   */
  IN,

  /**
   * Check if a date is equal to another.
   */
  EQUALS_DATE,

  /**
   * Check if a date is greater to another.
   */
  GREATER_DATE,

  /**
   * Check if a date is less than another.
   */
  LESS_DATE,

  /**
   * Check if a date is between two dates.
   */
  BETWEEN_DATE,
}