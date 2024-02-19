package com.nicholaspilotto.bookservice.controllers;

import com.nicholaspilotto.bookservice.annotation.AllowAnonymous;
import com.nicholaspilotto.bookservice.annotation.AuthorizedRoles;
import com.nicholaspilotto.bookservice.filters.BookFilters;
import com.nicholaspilotto.bookservice.models.dtos.BookCreationDto;
import com.nicholaspilotto.bookservice.models.dtos.BookDto;
import com.nicholaspilotto.bookservice.models.dtos.errors.ErrorResponse;
import com.nicholaspilotto.bookservice.models.dtos.status.PongDto;
import com.nicholaspilotto.bookservice.models.entities.Book;
import com.nicholaspilotto.bookservice.models.roles.Role;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @AuthorizedRoles(authorized = { Role.CUSTOMER, Role.ADMIN, Role.EMPLOYEE, Role.OWNER })
  public ResponseEntity<?> getAllBooks(final Pageable pageable, final BookFilters filters) {
    List<Book> books = this.bookService.getAllBooks(pageable).getContent();
    List<BookDto> response = Arrays.stream(mapper.map(books, BookDto[].class)).toList();
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
  @AuthorizedRoles(authorized = { Role.CUSTOMER, Role.ADMIN, Role.EMPLOYEE, Role.OWNER })
  public ResponseEntity<?> count(final Pageable pageable) {
    Long count = bookService.count(pageable);
    return new ResponseEntity<>(count, HttpStatus.OK);
  }

  /**
   * Get a particular {@link Book} by its identifier.
   *
   * @param id identifier of the desired {@link Book}.
   *
   * @return {@link BookDto} that corresponds to {@code id} if exists, otherwise, {@code null}.
   */
  @GetMapping("/{id}")
  @AuthorizedRoles(authorized = { Role.CUSTOMER, Role.ADMIN, Role.EMPLOYEE, Role.OWNER })
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Book book = bookService.getBookById(id).orElse(null);

    if (book == null) {
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND,
        "Book with id = %s has not been found.".formatted(id)
      );
      logger.warn("Book with id = %s has not been found.".formatted(id));
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    logger.info("Book with id = %s has been found".formatted(id));
    BookDto bookResponseDto = mapper.map(book, BookDto.class);
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Ping pong request.
   *
   * @return if the service is up, the response will be {@code pong}.
   */
  @GetMapping("/ping")
  @AllowAnonymous
  public ResponseEntity<?> ping() {
    return new ResponseEntity<>(new PongDto(), HttpStatus.OK);
  }

  /**
   * Create a new {@link Book}.
   *
   * @param payload new {@link Book} data.
   *
   * @return created {@link BookDto} data.
   */
  @PostMapping()
  @AuthorizedRoles(authorized = { Role.ADMIN, Role.OWNER })
  public ResponseEntity<?> create(@RequestBody BookCreationDto payload) {
    Book check = bookService.getBookByIsbn(payload.getIsbn()).orElse(null);

    if (check != null) {
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.CONFLICT,
        "Book with ISBN: %s already exists".formatted(payload.getIsbn())
      );
      logger.warn("Book with ISBN: %s already exists".formatted(payload.getIsbn()));
      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    Book newBook = mapper.map(payload, Book.class);
    newBook = bookService.createBook(newBook);

    BookDto dto = mapper.map(newBook, BookDto.class);
    logger.info("Book with ISBN: %s has been successfully created.".formatted(payload.getIsbn()));

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  /**
   * Update and existing {@link Book}.
   *
   * @param id identifier of the book to update.
   * @param payload new book data
   *
   * @return updated book.
   */
  @PatchMapping("/{id}")
  @AuthorizedRoles(authorized = { Role.ADMIN, Role.OWNER })
  public ResponseEntity<?> update(
    @PathVariable Long id,
    @RequestBody BookDto payload
  ) {
    Book check = bookService.getBookById(id).orElse(null);

    if (check == null) {
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST,
        "Book with id: %s does not exist.".formatted(id)
      );
      logger.warn("Book with id: %s does not exist.".formatted(id));
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    check = payload.overwrite(check);

    check = bookService.update(check);

    BookDto response = mapper.map(check, BookDto.class);

    logger.info("Book with id: %s has been updated".formatted(id));

    return  new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Delete an existing {@link Book}.
   *
   * @param isbn isbn of the book which to delete.
   *
   * @return {@code HTTP status: 200} if the book has been deleted, otherwise, {@code error HTTP status}.
   */
  @DeleteMapping("/{isbn}")
  @AuthorizedRoles(authorized = { Role.ADMIN })
  public ResponseEntity<?> delete(@PathVariable String isbn) {
    Book check = bookService.getBookByIsbn(isbn).orElse(null);

    if (check == null) {
      ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST,
        "Book with ISBN: %s does not exist".formatted(isbn)
      );
      logger.warn("Book with ISBN: %s does not exist".formatted(isbn));
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    bookService.delete(check);

    logger.info("Book with ISBN: %s has been successfully deleted.".formatted(isbn));
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
