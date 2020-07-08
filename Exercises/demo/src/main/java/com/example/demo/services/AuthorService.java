package com.example.demo.services;

import com.example.demo.data.model.Author;
import com.example.demo.services.models.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto create(AuthorDto authorDto);
    void update(AuthorDto authorDto);
    void delete(Long authorId);
    List<AuthorDto> allAuthors();
    Author getAuthor(Long id);
    AuthorDto getAuthorById(Long authorId);
}
