package com.nicholaspilotto.bookservice.services.interfaces;

import com.nicholaspilotto.bookservice.models.entities.Book;
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
}
