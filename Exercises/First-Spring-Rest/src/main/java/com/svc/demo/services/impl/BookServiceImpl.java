package com.svc.demo.services.impl;

import com.svc.demo.data.model.Author;
import com.svc.demo.data.model.Book;
import com.svc.demo.data.repositories.BookRepositories;
import com.svc.demo.services.AuthorService;
import com.svc.demo.services.BookService;
import com.svc.demo.services.models.AuthorDto;
import com.svc.demo.services.models.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepositories bookRepositories;
    private final AuthorService authorService;
    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories, AuthorService authorService, ModelMapper mapper) {
        this.bookRepositories = bookRepositories;
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return null;
    }

    @Override
    public void update(BookDto bookDto) {

    }

    @Override
    public void delete(Long bookId) {

    }

    @Override
    public List<BookDto> allBooks() {

        return bookRepositories.findAll().stream().map(book -> {
            return mapper.map(book, BookDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return null;
    }

    @Override
    public BookDto book(Long bookId, Long authorId) {
        Author authorDto = authorService.getAuthor(authorId);
        if (authorDto == null){
            return null;
        }
            List<Book> bookDto = authorDto.getBooks()
                    .stream()
                    .filter(book -> book.getId() == bookId)
                    .collect(Collectors.toList());
        if (bookDto.isEmpty()){
            return null;
        }
            return mapper.map(bookDto.get(0), BookDto.class);

    }

    @Override
    public List<BookDto> authorBooks(Long authorId) {
        AuthorDto authorDto = authorService.getAuthorById(authorId);
        if (authorDto == null){
            throw new EntityExistsException("The author does not exist!");
        }
        return authorDto.getBooks().stream().map(book -> {
            return mapper.map(book, BookDto.class);
        }).collect(Collectors.toList());
    }

}
