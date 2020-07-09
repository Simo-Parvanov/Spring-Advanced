package com.example.demo.web;

import com.example.demo.services.AuthorService;
import com.example.demo.services.models.AuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping
    @ResponseBody
    List<AuthorDto> getAuthor(){
        return  authorService.allAuthors();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId){
        AuthorDto author= authorService.getAuthorById(authorId);
        if (author == null){
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(author);

    }
    @PostMapping
    public ResponseEntity<AuthorDto> create(UriComponentsBuilder builder,
                                            @RequestBody AuthorDto authorDto){
        AuthorDto author =  authorService.create(authorDto);
       return ResponseEntity.created(builder.path("/author/{authorId}")
               .buildAndExpand(author.getId()).toUri()).build();
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<AuthorDto> delete(@PathVariable Long authorId){
        authorService.delete(authorId);
      return ResponseEntity.noContent().build();
    }
}
