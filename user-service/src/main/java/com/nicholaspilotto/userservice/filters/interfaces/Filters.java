package com.nicholaspilotto.userservice.filters.interfaces;

import org.springframework.data.jpa.domain.Specification;

/**
 * Represents the interface class for filters object.
 *
 * @param <T> object of the {@link Specification}.
 */
public interface Filters<T> {
  public abstract Specification<T> toSpecification();
}
