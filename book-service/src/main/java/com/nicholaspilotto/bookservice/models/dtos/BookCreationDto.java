package com.nicholaspilotto.bookservice.models.dtos;

import com.nicholaspilotto.bookservice.models.enums.BookGenre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Dto used to create a new book.
 */
public class BookCreationDto {
  @Size(min = 10)
  private String isbn;

  @Size(min = 1, max = 200)
  @NotEmpty
  private String title;

  @Min(value = 1L)
  @Max(value = 99_999L)
  private int pageNumber;

  @Min(value = 0L)
  @NotNull
  private BigDecimal price;

  @NotNull
  private Date pubDate;

  @NotNull
  private BookGenre genre;

  /**
   * Creates a new instance of {@link BookCreationDto} class.
   *
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber book number of pages.
   * @param price book price.
   * @param pubDate book publication date.
   * @param genre book genre.
   */
  public BookCreationDto(
    String isbn,
    String title,
    int pageNumber,
    BigDecimal price,
    Date pubDate,
    BookGenre genre
  ) {
    this.isbn = isbn;
    this.title = title;
    this.pageNumber = pageNumber;
    this.price = price;
    this.pubDate = pubDate;
    this.genre = genre;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Date getPubDate() {
    return pubDate;
  }

  public void setPubDate(Date pubDate) {
    this.pubDate = pubDate;
  }

  public BookGenre getGenre() {
    return genre;
  }

  public void setGenre(BookGenre genre) {
    this.genre = genre;
  }

  /**
   * Get the current object as string.
   *
   * @return String representing current object.
   */
  @Override
  public String toString() {
    return "BookCreationDto{"
      + "isbn='" + isbn + '\''
      + ", title='" + title + '\''
      + ", pageNumber=" + pageNumber
      + ", price=" + price
      + ", pubDate=" + pubDate
      + ", genre=" + genre
      + '}';
  }
}
