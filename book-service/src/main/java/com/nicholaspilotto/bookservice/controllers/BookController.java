package com.nicholaspilotto.bookservice.controllers;

import com.nicholaspilotto.bookservice.models.dtos.BookCreationDto;
import com.nicholaspilotto.bookservice.models.dtos.BookResponseDto;
import com.nicholaspilotto.bookservice.models.entities.Book;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  /**
   * Get a particular {@link Book} by its identifier.
   *
   * @param id identifier of the desired {@link Book}.
   *
   * @return {@link BookResponseDto} that corresponds to {@code id} if exists, otherwise, {@code null}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Book book = bookService.getBookById(id).orElse(null);

    if (book == null) {
      logger.warn("Book with id = %s has not been found.".formatted(id));
      return new ResponseEntity<>("Book cannot be found", HttpStatus.NOT_FOUND);
    }

    logger.info("Book with id = %s has been found".formatted(id));
    BookResponseDto bookResponseDto = mapper.map(book, BookResponseDto.class);
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Create a new {@link Book}.
   *
   * @param payload new {@link Book} data.
   *
   * @return created {@link BookResponseDto} data.
   */
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody BookCreationDto payload) {
    Book check = bookService.getBookByIsbn(payload.getIsbn()).orElse(null);

    if (check != null) {
      logger.warn("Book with ISBN: %s already exists".formatted(payload.getIsbn()));
      return new ResponseEntity<>("Book with provided ISBN already exists", HttpStatus.CONFLICT);
    }

    Book newBook = mapper.map(payload, Book.class);
    newBook = bookService.createBook(newBook);

    BookResponseDto dto = mapper.map(newBook, BookResponseDto.class);
    logger.info("Book with ISBN: %s has been successfully created.".formatted(payload.getIsbn()));

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }
}
