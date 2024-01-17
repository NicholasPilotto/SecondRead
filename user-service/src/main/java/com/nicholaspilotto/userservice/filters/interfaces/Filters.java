package com.nicholaspilotto.userservice.filters.interfaces;

import org.springframework.data.jpa.domain.Specification;

/**
 * Represents the interface class for filters object.
 *
 * @param <T> object of the {@link Specification}.
 */
public interface Filters<T> {
  /**
   * Creates a new {@link Specification} based on filter.
   *
   * @return {@link Specification} on filter.
   */
  public abstract Specification<T> toPredicate();
}
