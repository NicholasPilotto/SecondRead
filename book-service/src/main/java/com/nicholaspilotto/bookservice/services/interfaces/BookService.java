package com.nicholaspilotto.bookservice.services.interfaces;

import com.nicholaspilotto.bookservice.models.entities.Book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Represents the service layer for {@link com.nicholaspilotto.bookservice.models.entities.Book} entity.
 */
public interface BookService {
  /**
   * Get the list of all {@link Book} stored.
   *
   * @param pageable {@link Pageable} object to paginate current result.
   *
   * @return The list of all {@link Book} stored, eventually paginated.
   */
  public abstract Page<Book> getAllBooks(Pageable pageable);

  /**
   * Get the amount of {@link Book}, eventually paginated.
   *
   * @param pageable {@link Pageable} object of the current result.
   *
   * @return The amount of {@link Book}, eventually paginated by {@code pageable} object.
   */
  public abstract Long count(Pageable pageable);

  /**
   * Get a particular {@link Book} by its identifier.
   *
   * @param id identifier of the desired {@link Book}.
   *
   * @return {@link Book} that corresponds to {@code id} if exists, otherwise, {@code null}.
   */
  public abstract Optional<Book> getBookById(Long id);

  /**
   * Get a particular {@link Book} by ISBN code.
   *
   * @param isbn code of the desired {@link Book}.
   *
   * @return {@link Book} that corresponds to {@code ISBN} if exists, otherwise, {@code null}.
   */
  public abstract Optional<Book> getBookByIsbn(String isbn);

  /**
   * Create a new {@link Book}.
   *
   * @param book new {@link Book} data.
   *
   * @return Created {@link Book} data.
   */
  public abstract Book createBook(Book book);
}
