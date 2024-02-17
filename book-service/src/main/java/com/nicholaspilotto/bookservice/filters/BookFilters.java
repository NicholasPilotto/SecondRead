package com.nicholaspilotto.bookservice.filters;

import com.nicholaspilotto.bookservice.filters.enums.QueryOperator;
import com.nicholaspilotto.bookservice.filters.interefaces.Filters;
import com.nicholaspilotto.bookservice.models.entities.Book;
import com.nicholaspilotto.bookservice.models.enums.BookGenre;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.nicholaspilotto.bookservice.filters.specifications.BookFilterSpecificationBuilder.createSpecification;
import static org.springframework.data.jpa.domain.Specification.where;

public class BookFilters implements Filters<Book> {
  private String isbn;
  private String title;
  private Integer pageNumber;
  private BigDecimal price;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date pubDate;
  private BookGenre genre;

  /**
   * Create a new instance of {@link BookFilters} object.
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber book page number.
   * @param price book price.
   * @param pubDate book publication date.
   * @param genre book genre.
   */
  public BookFilters(String isbn, String title, int pageNumber, BigDecimal price, Date pubDate, BookGenre genre) {
    this.isbn = isbn;
    this.title = title;
    this.pageNumber = pageNumber;
    this.price = price;
    this.pubDate = pubDate;
    this.genre = genre;
  }

  @Override
  public Specification<Book> toPredicate() {
    List<FiltersSpecification> filtersSpecification = new ArrayList<>();

    if (isbn != null) {
      filtersSpecification.add(new FiltersSpecification("isbn", QueryOperator.LIKE, isbn, null));
    }

    if (title != null) {
      filtersSpecification.add(new FiltersSpecification("title", QueryOperator.LIKE, title, null));
    }

    if (pageNumber != null) {
      filtersSpecification.add(
        new FiltersSpecification(
          "pageNumber",
          QueryOperator.EQUALS,
          pageNumber.toString(),
          null
        )
      );
    }

    if (price != null) {
      filtersSpecification.add(
        new FiltersSpecification(
          "price",
          QueryOperator.EQUALS,
          price.toString(),
          null
        )
      );
    }

    if (pubDate != null) {
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      filtersSpecification.add(
        new FiltersSpecification(
          "pubDate",
          QueryOperator.EQUALS_DATE, formatter.format(pubDate), null
        )
      );
    }

    if (genre != null) {
      filtersSpecification.add(
        new FiltersSpecification(
          "genre",
          QueryOperator.EQUALS,
          "%s".formatted(genre.ordinal()),
          null
        )
      );
    }

    if (!filtersSpecification.isEmpty()) {
      Specification<Book> specification = where(createSpecification(filtersSpecification.remove(0)));

      for (FiltersSpecification input : filtersSpecification) {
        specification = specification.and(createSpecification(input));
      }

      return specification;
    }

    return null;
  }
}
