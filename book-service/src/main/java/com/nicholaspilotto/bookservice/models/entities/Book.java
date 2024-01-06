package com.nicholaspilotto.bookservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Represents a book object entity.
 */
@Table(name = "book")
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  @Size(min = 13, max = 13)
  private String isbn;

  @Size(min = 1, max = 200)
  @NotEmpty
  private String title;

  @Column(name = "page_number")
  @Min(value = 1L)
  @Max(value = 99_999L)
  private int pageNumber;

  @Min(value = 0L)
  @NotNull
  private BigDecimal price;

  @Column(name = "pub_date")
  @NotNull
  private LocalDateTime pubDate;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  /**
   * Initializes a new instance of {@link Book}.
   *
   * @param id book identifier.
   * @param isbn book isbn code.
   * @param title book title.
   * @param pageNumber book number of pages.
   * @param price book price.
   * @param pubDate book publication date.
   * @param createdAt book creation date.
   * @param updatedAt book date of the last update.
   */
  public Book(
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return pageNumber == book.pageNumber && Objects.equals(id, book.id) && Objects.equals(
      isbn,
      book.isbn
    ) && Objects.equals(title, book.title) && Objects.equals(
      price,
      book.price
    ) && Objects.equals(pubDate, book.pubDate) && Objects.equals(
      createdAt,
      book.createdAt
    ) && Objects.equals(updatedAt, book.updatedAt);
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
