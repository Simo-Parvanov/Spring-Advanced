package com.example.demo.services.models;

import com.example.demo.data.model.Book;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AuthorDto {
    private Long id;
    @NotNull
    private String name;
    private List<Book> books;

    public AuthorDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
