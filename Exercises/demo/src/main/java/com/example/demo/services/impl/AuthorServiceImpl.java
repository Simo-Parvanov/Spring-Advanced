package com.example.demo.services.impl;

import com.example.demo.data.model.Author;
import com.example.demo.data.repositories.AuthorRepositories;
import com.example.demo.services.AuthorService;
import com.example.demo.services.models.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositories authorRepositories;
    private final ModelMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepositories authorRepositories, ModelMapper mapper) {
        this.authorRepositories = authorRepositories;
        this.mapper = mapper;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = mapper.map(authorDto, Author.class);
        authorRepositories.saveAndFlush(author);
        return authorDto;
    }

    @Override
    public void update(AuthorDto authorDto) {

    }

    @Override
    public void delete(Long authorId) {
        Optional<Author> author = authorRepositories.findById(authorId);
        if (author.isEmpty()){
            throw new EntityExistsException("The author does not exist!");
        }

        authorRepositories.delete(author.get());
    }

    @Override
    public List<AuthorDto> allAuthors() {
        return authorRepositories.findAll().stream().map(author -> {
            return mapper.map(author, AuthorDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepositories.findById(id).orElse(null);
    }

    @Override
    public AuthorDto getAuthorById(Long authorId) {
        Optional<Author> authorDto = authorRepositories.findById(authorId);
        return authorDto.isEmpty() ? null : mapper.map(authorDto.get(), AuthorDto.class);
    }
}
