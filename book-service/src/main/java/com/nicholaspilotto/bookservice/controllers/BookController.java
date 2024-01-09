package com.nicholaspilotto.bookservice.controllers;

import com.nicholaspilotto.bookservice.models.entities.Book;
import com.nicholaspilotto.bookservice.models.entities.dtos.BookResponseDto;
import com.nicholaspilotto.bookservice.services.BookServiceImplementation;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for {@link Book} entity.
 */
@RestController
@RequestMapping("/book")
public class BookController {
  private final BookServiceImplementation bookService;

  private final ModelMapper mapper;

  private final Logger logger;

  /**
   * Creates a new instance of {@link BookController} object.
   *
   * @param bookService {@link BookServiceImplementation} injected reference.
   * @param mapper {@link ModelMapper} injected reference.
   */
  @Autowired
  public BookController(BookServiceImplementation bookService, ModelMapper mapper) {
    this.bookService = bookService;
    this.mapper = mapper;
    this.logger = LoggerFactory.getLogger(BookController.class);
  }

  /**
   * Get the list of all {@link Book} stored.
   *
   * @param pageable {@link Pageable} object of the current result.
   *
   * @return The list of all {@link Book}, eventually paginated.
   */
  @GetMapping()
  public ResponseEntity<?> getAllBooks(final Pageable pageable) {
    List<Book> books = this.bookService.getAllBooks(pageable).getContent();
    List<BookResponseDto> response = Arrays.stream(mapper.map(books, BookResponseDto[].class)).toList();
    logger.info("Books has been requested.");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Get the total amount of {@link Book}, eventually paginated.
   *
   * @param pageable {@link Pageable} of the current result.
   *
   * @return Amount of {@link Book}, eventually paginated with {@code pageable}.
   */
  @GetMapping("/count")
  public ResponseEntity<?> count(final Pageable pageable) {
    Long count = bookService.count(pageable);
    return new ResponseEntity<>(count, HttpStatus.OK);
  }
}
