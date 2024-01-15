package com.nicholaspilotto.userservice.filters.specifications;

import com.nicholaspilotto.userservice.filters.FiltersSpecification;
import com.nicholaspilotto.userservice.models.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 * Creates a new {@link Specification} from {@link FiltersSpecification} object.
 */
public class UserFilterSpecificationBuilder {
  /**
   * Creates a new {@link Specification} object.
   *
   * @param filters object containing filters data.
   *
   * @return {@link Specification} filled with {@link FiltersSpecification}.
   */
  public static Specification<User> createSpecification(FiltersSpecification filters) {
    return switch (filters.getOperator()) {
      case EQUALS -> (root, query, criteriaBuilder) ->
          criteriaBuilder.equal(root.get(filters.getField()),
            castToRequiredType(root.get(filters.getField()).getJavaType(), filters.getValue()));
      case NOT_EQ -> (root, query, criteriaBuilder) ->
        criteriaBuilder.notEqual(
          root.get(filters.getField()),
          castToRequiredType(root.get(filters.getField())
                                 .getJavaType(), filters.getValue())
        );
      case GREATER_THAN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.gt(
          root.get(filters.getField()),
          (Number) castToRequiredType(root.get(filters.getField())
                                          .getJavaType(), filters.getValue())
        );
      case LESS_THAN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.lt(
          root.get(filters.getField()),
          (Number) castToRequiredType(root.get(filters.getField())
                                          .getJavaType(), filters.getValue())
        );
      case LIKE -> (root, query, criteriaBuilder) ->
        criteriaBuilder.like(root.get(filters.getField()), "%" + filters.getValue() + "%");
      case IN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.in(root.get(filters.getField()))
                       .value(castToRequiredType(root.get(filters.getField())
                                                     .getJavaType(), filters.getValues()));
      default -> throw new RuntimeException("Operation not supported yet");
    };
  }

  private static Object castToRequiredType(Class fieldType, String value) {
    if (fieldType.isAssignableFrom(Double.class)) {
      return Double.valueOf(value);
    } else if (fieldType.isAssignableFrom(Integer.class)) {
      return Integer.valueOf(value);
    } else if (Enum.class.isAssignableFrom(fieldType)) {
      return Enum.valueOf(fieldType, value);
    }

    return null;
  }

  private static Object castToRequiredType(Class fieldType, List<String> value) {
    List<Object> lists = new ArrayList<>();
    for (String s : value) {
      lists.add(castToRequiredType(fieldType, s));
    }
    return lists;
  }
}
