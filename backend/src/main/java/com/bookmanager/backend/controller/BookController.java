package com.bookmanager.backend.controller;

import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class BookController {


    private final BookService bookService;



    public BookController(
            BookService bookService
    ) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> save(
            @RequestBody BookRequest dto,
            Authentication authentication
    ) {


        BookResponse response =
                bookService.save(
                        dto,
                        authentication.getName()
                );


        return ResponseEntity.ok(response);
    }

    @GetMapping
        public ResponseEntity<List<BookResponse>> findAll(

                @RequestParam(
                        required = false
                )
                String title,

                Authentication authentication

        ) {


        List<BookResponse> books =
                bookService.findAll(

                        authentication.getName(),

                        title

                );



        return ResponseEntity.ok(books);
        }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable Long id,
            @RequestBody BookRequest dto,
            Authentication authentication
    ) {


        BookResponse response =
                bookService.update(
                        id,
                        dto,
                        authentication.getName()
                );


        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            Authentication authentication
    ) {


        bookService.delete(
                id,
                authentication.getName()
        );


        return ResponseEntity.ok(
                "Livro removido com sucesso"
        );
    }

    @GetMapping("/{id}")
                public ResponseEntity<BookResponse> findById(
                        @PathVariable Long id,
                        Authentication authentication
                ) {


                BookResponse response =
                        bookService.findById(
                                id,
                                authentication.getName()
                        );


                return ResponseEntity.ok(response);
        }

}