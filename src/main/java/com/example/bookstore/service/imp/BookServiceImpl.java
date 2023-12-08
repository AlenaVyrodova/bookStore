package com.example.bookstore.service.imp;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookSearchParametersDto;
import com.example.bookstore.dto.book.CreateBookRequestDto;
import com.example.bookstore.exception.EntityNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.specification.BookSpecificationBuilder;
import com.example.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder specificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {

        return bookRepository.findAll(pageable)
                .stream().map(bookMapper::toDto).toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id" + id));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto bookRequestDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("The book with this " + id + " doesn't exist");
        }
        Book bookUpdate = bookMapper.toModel(bookRequestDto);
        bookUpdate.setId(id);
        return bookMapper.toDto(bookRepository.save(bookUpdate));
    }

    @Override
    public List<BookDto> searchBookByParams(BookSearchParametersDto bookSearchParameters,
                                            Pageable pageable) {
        Specification<Book> bookSpecification = specificationBuilder.build(bookSearchParameters);
        return bookRepository.findAll(bookSpecification, pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

}

