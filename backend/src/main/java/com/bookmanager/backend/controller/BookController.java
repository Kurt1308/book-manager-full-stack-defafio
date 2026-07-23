package com.bookmanager.backend.controller;

import com.bookmanager.backend.model.Book;
import com.bookmanager.backend.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {


    private final BookRepository repository;


    public BookController(BookRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<Book> findAll() {
        return repository.findAll();
    }


    @PostMapping
    public Book save(@RequestBody Book book) {
        return repository.save(book);
    }
}