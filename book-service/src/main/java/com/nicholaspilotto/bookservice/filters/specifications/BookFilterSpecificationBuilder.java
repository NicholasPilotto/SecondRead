package com.nicholaspilotto.bookservice.filters.specifications;

import com.nicholaspilotto.bookservice.filters.FiltersSpecification;
import com.nicholaspilotto.bookservice.models.entities.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 * Creates a new {@link Specification} from {@link FiltersSpecification} object.
 */
public class BookFilterSpecificationBuilder {
  /**
   * Creates a new {@link Specification} object.
   *
   * @param filters object containing filters data.
   *
   * @return {@link Specification} filled with {@link FiltersSpecification}.
   */
  public static Specification<Book> createSpecification(FiltersSpecification filters) {
    return switch (filters.getOperator()) {
      case EQUALS -> (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(filters.getField()), filters.getValue());

      case NOT_EQ -> (root, query, criteriaBuilder) ->
        criteriaBuilder.notEqual(root.get(filters.getField()), filters.getValue());

      case GREATER_THAN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.greaterThan(root.get(filters.getField()), filters.getValue());

      case LESS_THAN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.lessThan(root.get(filters.getField()), filters.getValue());

      case LIKE -> (root, query, criteriaBuilder) ->
        criteriaBuilder.like(root.get(filters.getField()), "%" + filters.getValue() + "%");

      case IN -> (root, query, criteriaBuilder) ->
        criteriaBuilder.in(root.get(filters.getField()))
                       .value(castToRequiredType(root.get(filters.getField())
                                                     .getJavaType(), filters.getValues()));

      case EQUALS_DATE -> (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(filters.getField()), stringToDate(filters.getValue()));

      case GREATER_DATE -> (root, query, criteriaBuilder) ->
        criteriaBuilder.greaterThan(root.get(filters.getField()), stringToDate(filters.getValue()));

      case LESS_DATE -> (root, query, criteriaBuilder) ->
        criteriaBuilder.lessThan(root.get(filters.getField()), stringToDate(filters.getValue()));

      case BETWEEN_DATE -> (root, query, criteriaBuilder) -> {
        List<Date> dates = separateDate(filters.getValue());
        return criteriaBuilder.between(root.get(filters.getField()), dates.get(0), dates.get(1));
      };
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

  /**
   * Creates a new {@link Date} object from a {@link String}.
   *
   * @param dateString {@link String} from which to build new date.
   *
   * @return Created {@link Date} object.
   */
  private static Date stringToDate(String dateString) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    try {
      return formatter.parse(dateString);
    } catch (ParseException exception) {
      return null;
    }
  }

  /**
   * Creates an array of {@link Date} from a string containing dates separated by {@code ;}.
   *   {@code 1999-08-25;2024-01-17} is mapped as {@code 1999-08-25, 2024-01-17}.
   *
   * @param dates {@link String} containing dates.
   *
   * @return {@link List} of {@link Date}.
   *
   */
  private static List<Date> separateDate(String dates) {
    return Arrays.stream(dates.split(";"))
                 .map(BookFilterSpecificationBuilder::stringToDate)
                 .toList();
  }
}
