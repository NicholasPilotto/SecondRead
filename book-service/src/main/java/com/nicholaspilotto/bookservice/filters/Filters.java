package com.nicholaspilotto.bookservice.filters;

import org.springframework.data.jpa.domain.Specification;

/**
 * Represents the interface class for filter object.
 *
 * @param <T> object of the {@link Specification}.
 */
public interface Filters<T> {
  /**
   * Creates a new {@link Specification} base on filter.
   *
   * @return {@link  Specification} on filter.
   */
  Specification<T> toPredicate();
}
