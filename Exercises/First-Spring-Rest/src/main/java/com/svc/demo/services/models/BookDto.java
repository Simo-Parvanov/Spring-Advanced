package com.svc.demo.services.models;

import com.svc.demo.data.model.Author;

import javax.persistence.*;

public class BookDto {
    private Long id;
    private String title;
    private Author author;

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
