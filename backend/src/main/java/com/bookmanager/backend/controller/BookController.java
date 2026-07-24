package com.bookmanager.backend.controller;

import com.bookmanager.backend.dto.BookResponse;
import com.bookmanager.backend.model.Book;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.BookRepository;
import com.bookmanager.backend.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {


    private final BookRepository repository;
    private final UserRepository userRepository;


    public BookController(
            BookRepository repository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    @PostMapping
    public Book save(
            @RequestBody Book book,
            Authentication authentication
    ) {


        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();


        book.setUser(user);


        return repository.save(book);
    }

    @GetMapping
    public List<BookResponse> findAll(Authentication authentication) {


        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();


        return repository
                .findByUserId(user.getId())
                .stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getYear()
                ))
                .toList();
    }

    @PutMapping("/{id}")
        public ResponseEntity<BookResponse> update(
                @PathVariable Long id,
                @RequestBody Book request,
                Authentication authentication
        ) {


            User user = userRepository
                    .findByEmail(authentication.getName())
                    .orElseThrow();



            Book book = repository
                    .findByIdAndUserId(
                            id,
                            user.getId()
                    )
                    .orElse(null);



            if(book == null){

                return ResponseEntity
                        .notFound()
                        .build();
            }



            book.setTitle(
                    request.getTitle()
            );


            book.setAuthor(
                    request.getAuthor()
            );


            book.setYear(
                    request.getYear()
            );



            Book updated =
                    repository.save(book);



            return ResponseEntity.ok(
                    new BookResponse(
                            updated.getId(),
                            updated.getTitle(),
                            updated.getAuthor(),
                            updated.getYear()
                    )
            );

    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> delete(
        @PathVariable Long id,
        Authentication authentication
) {


    User user = userRepository
            .findByEmail(authentication.getName())
            .orElseThrow();



    Book book = repository
            .findByIdAndUserId(
                    id,
                    user.getId()
            )
            .orElse(null);



    if(book == null){

        return ResponseEntity
                .notFound()
                .build();
    }



    repository.delete(book);



    return ResponseEntity.ok(
            "Livro removido com sucesso"
    );
}
}