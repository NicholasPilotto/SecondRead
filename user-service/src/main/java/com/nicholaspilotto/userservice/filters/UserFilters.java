package com.nicholaspilotto.userservice.filters;

import static com.nicholaspilotto.userservice.filters.specifications.UserFilterSpecificationBuilder.createSpecification;
import static org.springframework.data.jpa.domain.Specification.where;

import com.nicholaspilotto.userservice.filters.interfaces.Filters;
import com.nicholaspilotto.userservice.filters.specifications.enums.QueryOperator;
import com.nicholaspilotto.userservice.models.entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents a filter object for {@link User} model.
 */
public class UserFilters implements Filters<User> {
  private String firstName;
  private String lastName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;
  private String phoneNumber;
  private String email;

  /**
   * Create a new instance of {@link UserFilters} object.
   *
   * @param firstName first name filter.
   * @param lastName last name filter.
   * @param birthday birthday filter.
   * @param phoneNumber phone number fìlter.
   * @param email email address filter.
   */
  public UserFilters(String firstName, String lastName, Date birthday, String phoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthdate = birthday;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  @Override
  public Specification<User> toPredicate() {
    List<FiltersSpecification> filtersSpecification = new ArrayList<>();

    if (firstName != null) {
      filtersSpecification.add(new FiltersSpecification("firstName", QueryOperator.LIKE, firstName, null));
    }

    if (lastName != null) {
      filtersSpecification.add(new FiltersSpecification("lastName", QueryOperator.LIKE, lastName, null));
    }

    if (birthdate != null) {
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

      filtersSpecification.add(
        new FiltersSpecification(
          "birthDate",
          QueryOperator.EQUALS_DATE, formatter.format(birthdate),
          null
        )
      );
    }

    if (phoneNumber != null) {
      filtersSpecification.add(
        new FiltersSpecification("phoneNumber", QueryOperator.EQUALS, phoneNumber, null)
      );
    }

    if (email != null) {
      filtersSpecification.add(
        new FiltersSpecification("email", QueryOperator.EQUALS, email, null)
      );
    }

    if (!filtersSpecification.isEmpty()) {
      Specification<User> specification = where(createSpecification(filtersSpecification.remove(0)));
      for (FiltersSpecification input : filtersSpecification) {
        specification = specification.and(createSpecification(input));
      }
      return specification;
    }

    return null;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
