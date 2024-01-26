package com.nicholaspilotto.bookservice.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto used to create a new book.
 */
public class BookCreationDto {
  @Size(min = 13, max = 13)
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
  private LocalDateTime pubDate;

  /**
   * Creates a new instance of {@link BookCreationDto} class.
   *
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber book number of pages.
   * @param price book price.
   * @param pubDate book publication date.
   */
  public BookCreationDto(String isbn, String title, int pageNumber, BigDecimal price, LocalDateTime pubDate) {
    this.isbn = isbn;
    this.title = title;
    this.pageNumber = pageNumber;
    this.price = price;
    this.pubDate = pubDate;
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

  public LocalDateTime getPubDate() {
    return pubDate;
  }

  public void setPubDate(LocalDateTime pubDate) {
    this.pubDate = pubDate;
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
      + '}';
  }
}
