package com.example.demo;

import com.example.demo.data.model.Author;
import com.example.demo.data.model.Book;
import com.example.demo.data.repositories.AuthorRepositories;
import com.example.demo.data.repositories.BookRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestApplicationInit implements CommandLineRunner {

    private final AuthorRepositories authorRepository;
    private final BookRepositories bookRepository;

    RestApplicationInit(AuthorRepositories authorRepository,
                        BookRepositories bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorRepository.count() == 0) {
            initJovkov();
            initNikolaiHaitov();
            initDimitarTalev();
            initElinPelin();
            initVazov();
        }
    }

    private void initNikolaiHaitov() {
        initAuthor("Николай Хайтов",
                "Диви Разкази"
        );
    }

    private void initDimitarTalev() {
        initAuthor("Димитър Талев",
                "Тютюн"
        );
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин",
                "Пижо и Пендо",
                "Ян Бибиян на луната",
                "Под манастирската лоза"
        );
    }

    private void initVazov() {
        initAuthor("Иван Вазов",
                "Пряпорец и Гусла",
                "Под Игото",
                "Тъгите на България"
        );
    }

    private void initJovkov() {

        initAuthor("Йордан Йовков",
                "Старопланински легенди",
                "Чифликът край границата");
    }

    private void initAuthor(String authorName, String... books) {
        Author author = new Author();
        author.setName(authorName);

        author = authorRepository.save(author);

        List<Book> allBooks = new ArrayList<>();

        for (String book : books) {
            Book aBook = new Book();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            allBooks.add(aBook);
        }

        bookRepository.saveAll(allBooks);
    }
}
