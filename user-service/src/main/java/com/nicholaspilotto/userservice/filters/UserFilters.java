package com.nicholaspilotto.userservice.filters;

import com.nicholaspilotto.userservice.filters.interfaces.Filters;
import com.nicholaspilotto.userservice.filters.specifications.UserFilterSpecificationBuilder;
import com.nicholaspilotto.userservice.filters.specifications.enums.QueryOperator;
import com.nicholaspilotto.userservice.models.entities.User;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

/**
 * Represents a filter object for {@link User} model.
 */
public class UserFilters implements Filters<User> {
  private String firstName;
  private String lastName;
  private Date birthday;
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
    this.birthday = birthday;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  @Override
  public Specification<User> toSpecification() {
    Specification<User> specification = Specification.where(UserFilterSpecificationBuilder.createSpecification(
      new FiltersSpecification("firstName", QueryOperator.LIKE, firstName, null)
    ));

    specification.and(
      UserFilterSpecificationBuilder.createSpecification(
        new FiltersSpecification("lastName", QueryOperator.LIKE, lastName, null)
      )
    );

    if (birthday != null) {
      specification.and(
        UserFilterSpecificationBuilder.createSpecification(
          new FiltersSpecification("birthday", QueryOperator.EQUALS, birthday.toString(), null)
        )
      );
    }

    specification.and(
      UserFilterSpecificationBuilder.createSpecification(
        new FiltersSpecification("phoneNumber", QueryOperator.LIKE, phoneNumber, null)
      )
    );

    specification.and(
      UserFilterSpecificationBuilder.createSpecification(
        new FiltersSpecification("email", QueryOperator.LIKE, email, null)
      )
    );

    return specification;
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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
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
