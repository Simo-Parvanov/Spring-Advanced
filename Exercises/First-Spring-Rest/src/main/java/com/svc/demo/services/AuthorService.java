package com.svc.demo.services;

import com.svc.demo.data.model.Author;
import com.svc.demo.services.models.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto create(AuthorDto authorDto);
    void update(AuthorDto authorDto);
    void delete(Long authorId);
    List<AuthorDto> allAuthors();
    Author getAuthor(Long id);
    AuthorDto getAuthorById(Long authorId);
}
