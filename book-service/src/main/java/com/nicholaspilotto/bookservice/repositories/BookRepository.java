package com.nicholaspilotto.bookservice.repositories;

import com.nicholaspilotto.bookservice.models.entities.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents the repository layer for the {@link Book} entity.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
  /**
   * Find {@link Book} by ISBN code.
   *
   * @param isbn code of the desired {@link Book}.
   *
   * @return {@link Book} that corresponds to {@code ISBN} if exists, otherwise, {@code null}.
   */
  Optional<Book> findByIsbn(String isbn);
}
