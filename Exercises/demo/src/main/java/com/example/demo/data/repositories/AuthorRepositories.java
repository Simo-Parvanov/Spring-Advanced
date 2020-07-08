package com.example.demo.data.repositories;

import com.example.demo.data.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositories extends JpaRepository<Author, Long> {
    void delete(Author author);
}
