package com.nicholaspilotto.userservice.filters;

import com.nicholaspilotto.userservice.filters.specifications.enums.QueryOperator;
import java.util.List;

/**
 * Represents a filters model.
 */
public class FiltersSpecification {
  private String field;
  private QueryOperator operator;
  private String value;
  private List<String> values;

  /**
   * Creates a new instance of {@link FiltersSpecification}.
   *
   * @param field model property to filter.
   * @param operator {@link QueryOperator} specify which operation to do.
   * @param value comparator value.
   * @param values list of values used in {@code IN} operator.
   */
  public FiltersSpecification(String field, QueryOperator operator, String value, List<String> values) {
    this.field = field;
    this.operator = operator;
    this.value = value;
    this.values = values;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public QueryOperator getOperator() {
    return operator;
  }

  public void setOperator(QueryOperator operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }
}
