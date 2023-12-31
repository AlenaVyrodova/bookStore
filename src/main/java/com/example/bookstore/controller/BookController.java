package com.example.bookstore.controller;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookSearchParametersDto;
import com.example.bookstore.dto.book.CreateBookRequestDto;
import com.example.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get all book", description = "Get a list of all available books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "get book by id", description = "get the specified book by id ")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Create as new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update book by id", description = "update book by id")
    public BookDto updateBookById(@PathVariable Long id,
                                  @RequestBody @Valid CreateBookRequestDto bookRequestDto) {
        return bookService.updateById(id, bookRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book by id", description = "Delete specified book by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<BookDto> searchBooks(BookSearchParametersDto bookSearchParameters,
                                     Pageable pageable) {
        return bookService.searchBookByParams(bookSearchParameters, pageable);
    }
}
