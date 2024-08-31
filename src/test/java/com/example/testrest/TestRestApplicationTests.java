package com.example.testrest;

import com.example.testrest.model.Book;
import com.example.testrest.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class TestRestApplicationTests {
    private final BookRepository repository = BookRepository.getRepository();

    @Test
    void checkCreate() {
        Book book = new Book(1, "title", "author", 2002);

        try {
            repository.createBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Book gotBook = repository.getBook(1);
            Assertions.assertEquals(book.equals(gotBook), true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


