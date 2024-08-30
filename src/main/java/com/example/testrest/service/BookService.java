package com.example.testrest.service;


import com.example.testrest.model.Book;

import com.example.testrest.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

public class BookService {
    private final BookRepository repository = BookRepository.getRepository();
//
    public void createBook(Book book) {
        try {
            repository.createBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBook(int id) {
        try {
            return repository.getBook(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        try {
            repository.update(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int id ) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
