package com.nicholaspilotto.bookservice.models.dtos;

import com.nicholaspilotto.bookservice.models.entities.Book;
import com.nicholaspilotto.bookservice.models.enums.BookGenre;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a book response dto.
 */
public class BookDto {
  private Long id;
  private String isbn;
  private String title;
  private Integer pageNumber;
  private BigDecimal price;
  private Date pubDate;
  private BookGenre genre;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  /**
   * Initializes a new instance of {@link BookDto}.
   *
   * @param id book identifier.
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber book number of pages.
   * @param price book price.
   * @param pubDate book publication date.
   * @param genre book genre.
   * @param createdAt book creation date.
   * @param updatedAt book date of the last update.
   */
  public BookDto(
    Long id,
    String isbn,
    String title,
    int pageNumber,
    BigDecimal price,
    Date pubDate,
    BookGenre genre,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.isbn = isbn;
    this.title = title;
    this.pageNumber = pageNumber;
    this.price = price;
    this.pubDate = pubDate;
    this.genre = genre;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  /**
   * {@link BookDto} default constructor.
   */
  public BookDto() {
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

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
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

  /**
   * Overwrite existing {@link Book} with current dto.
   *
   * @param existing entity data.
   *
   * @return existing entity overwritten with current dto data.
   */
  public Book overwrite(Book existing) {
    if (this.isbn == null) {
      this.isbn = existing.getIsbn();
    }

    if (this.title == null) {
      this.title = existing.getTitle();
    }
    if (this.pageNumber == null) {
      this.pageNumber = existing.getPageNumber();
    }
    if (this.price == null) {
      this.price = existing.getPrice();
    }
    if (this.pubDate == null) {
      this.pubDate = existing.getPubDate();
    }

    return existing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookDto that = (BookDto) o;
    return Objects.equals(pageNumber, that.pageNumber) && Objects.equals(id, that.id) && Objects.equals(
      isbn,
      that.isbn
    ) && Objects.equals(title, that.title) && Objects.equals(
      price,
      that.price
    ) && Objects.equals(pubDate, that.pubDate) && Objects.equals(
      createdAt,
      that.createdAt
    ) && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, isbn, title, pageNumber, price, pubDate, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "Book{"
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
