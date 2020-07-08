package com.example.demo.data.repositories;

import com.example.demo.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<Book, Long> {
}
