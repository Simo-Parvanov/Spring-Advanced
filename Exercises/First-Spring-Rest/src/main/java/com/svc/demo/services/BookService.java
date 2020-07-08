package com.svc.demo.services;

import com.svc.demo.data.model.Author;
import com.svc.demo.services.models.AuthorDto;
import com.svc.demo.services.models.BookDto;

import java.util.List;

public interface BookService {

    BookDto create(BookDto bookDto);
    void update(BookDto bookDto);
    void delete(Long bookId);
    List<BookDto> allBooks();
    BookDto getBookById(Long bookId);
    BookDto book(Long bookId, Long authorId);
    List<BookDto> authorBooks(Long authorId);

}
