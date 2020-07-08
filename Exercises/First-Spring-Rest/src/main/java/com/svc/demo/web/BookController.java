package com.svc.demo.web;

import com.svc.demo.services.BookService;
import com.svc.demo.services.models.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable Long authorId){
        List<BookDto> books =  bookService.authorBooks(authorId);
        if (books.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(books);
    }
    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<BookDto> getBooks(@PathVariable Long authorId,
                                            @PathVariable Long bookId){
        BookDto book =  bookService.book(bookId, authorId);
        System.out.println();
        if (book == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }

}
