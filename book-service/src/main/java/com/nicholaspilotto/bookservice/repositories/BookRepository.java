package com.nicholaspilotto.bookservice.repositories;

import com.nicholaspilotto.bookservice.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents the repository layer for the {@link Book} entity.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
