package com.svc.demo.data.repositories;

import com.svc.demo.data.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepositories extends JpaRepository<Author, Long> {
    void delete(Author author);
}
