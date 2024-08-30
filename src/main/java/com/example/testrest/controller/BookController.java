package com.example.testrest.controller;

import com.example.testrest.model.Book;
import com.example.testrest.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class BookController {
    private BookService service;

    @PostMapping("createBook")
    void createBook(@RequestBody Book newBook) {
        service.createBook(newBook);
    }

    @GetMapping("getBook/{id}")
    Book getBook(@PathVariable int id) {
        return service.getBook(id);
    }

    @PutMapping("updateBook")
    void updateBook(@RequestBody Book book) {
        service.updateBook(book);
    }

    @DeleteMapping("deleteBook/{id}")
    void deleteBook(@PathVariable int id) {
        service.deleteBook(id);
    }




}
