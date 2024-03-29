package com.nicholaspilotto.bookservice.services;

import com.nicholaspilotto.bookservice.models.entities.Book;
import com.nicholaspilotto.bookservice.repositories.BookRepository;
import com.nicholaspilotto.bookservice.services.interfaces.BookService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation service class of {@link BookService}.
 */
@Service
public class BookServiceImplementation implements BookService {
  private final BookRepository bookRepository;

  /**
   * Creates a new instance of {@link BookServiceImplementation} object.
   *
   * @param bookRepository {@link BookRepository} injected reference.
   */
  @Autowired
  public BookServiceImplementation(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Page<Book> getAllBooks(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }

  @Override
  public Long count(Pageable pageable) {
    return getAllBooks(pageable).getTotalElements();
  }

  @Override
  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public Optional<Book> getBookByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn);
  }

  @Override
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book update(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public void delete(Book existing) {
    bookRepository.delete(existing);
  }
}
