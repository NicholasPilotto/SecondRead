package com.nicholaspilotto.bookservice.models.entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * {@code Data Transfer Object} used to expose {@link com.nicholaspilotto.bookservice.models.entities.Book} data.
 */
public class BookResponseDto {
  private Long id;
  private String isbn;
  private String title;
  private int pageNumber;
  private BigDecimal price;
  private LocalDateTime pubDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  /**
   * Creates a new instance of {@link BookResponseDto} object.
   */
  public BookResponseDto() { }

  /**
   * Creates a new instance of {@link BookResponseDto} object.
   *
   * @param id book identifier.
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber number of pages of the book.
   * @param price book price.
   * @param pubDate book publication date.
   * @param createdAt book creation date.
   * @param updatedAt last book update date.
   */
  public BookResponseDto(
    Long id,
    String isbn,
    String title,
    int pageNumber,
    BigDecimal price,
    LocalDateTime pubDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.isbn = isbn;
    this.title = title;
    this.pageNumber = pageNumber;
    this.price = price;
    this.pubDate = pubDate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "BookResponseDto{"
      + "id=" + id
      + ", isbn='" + isbn + '\''
      + ", title='" + title + '\''
      + ", pageNumber=" + pageNumber
      + ", price=" + price
      + ", pubDate=" + pubDate
      + ", createdAt=" + createdAt
      + ", updatedAt=" + updatedAt
      + '}';
  }
}
