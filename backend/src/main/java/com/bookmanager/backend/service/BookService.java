package com.bookmanager.backend.service;


import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.model.Book;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.BookRepository;
import com.bookmanager.backend.repository.UserRepository;

import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class BookService {


    private final BookRepository bookRepository;

    private final UserRepository userRepository;



    public BookService(
            BookRepository bookRepository,
            UserRepository userRepository
    ) {

        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }





    public BookResponse save(
            BookRequest request,
            String email
    ) {


        User user = userRepository
                .findByEmail(email)
                .orElseThrow();



        Book book = new Book();



        book.setTitle(
                request.getTitle()
        );


        book.setAuthor(
                request.getAuthor()
        );


        book.setYear(
                request.getYear()
        );


        book.setDescription(
                request.getDescription()
        );


        book.setUser(user);



        Book saved = bookRepository.save(book);



        return new BookResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor(),
                saved.getYear(),
                saved.getDescription()
        );
    }







    public Page<BookResponse> findAll(
        String email,
        String title,
        Pageable pageable
) {


    User user = userRepository
            .findByEmail(email)
            .orElseThrow();



    Page<Book> books;



    if(title != null && !title.isBlank()) {


        books =
                bookRepository
                        .findByUser_IdAndTitleContainingIgnoreCase(
                                user.getId(),
                                title,
                                pageable
                        );


    } else {


        books =
                bookRepository
                        .findByUser_Id(
                                user.getId(),
                                pageable
                        );

    }




    return books.map(book ->

            new BookResponse(

                    book.getId(),

                    book.getTitle(),

                    book.getAuthor(),

                    book.getYear(),

                    book.getDescription()

            )

    );

}







    public BookResponse findById(
            Long id,
            String email
    ) {


        Book book = bookRepository
                .findByIdAndUser_Email(
                        id,
                        email
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Livro não encontrado"
                        )
                );



        return new BookResponse(

                book.getId(),

                book.getTitle(),

                book.getAuthor(),

                book.getYear(),

                book.getDescription()

        );
    }








    public BookResponse update(
            Long id,
            BookRequest dto,
            String email
    ) {


        User user = userRepository
                .findByEmail(email)
                .orElseThrow();



        Book book = bookRepository
                .findByIdAndUser_Id(
                        id,
                        user.getId()
                )
                .orElseThrow();




        book.setTitle(
                dto.getTitle()
        );



        book.setAuthor(
                dto.getAuthor()
        );



        book.setYear(
                dto.getYear()
        );



        book.setDescription(
                dto.getDescription()
        );




        Book updated =
                bookRepository.save(book);




        return new BookResponse(

                updated.getId(),

                updated.getTitle(),

                updated.getAuthor(),

                updated.getYear(),

                updated.getDescription()

        );
    }








    public void delete(
            Long id,
            String email
    ) {


        User user = userRepository
                .findByEmail(email)
                .orElseThrow();



        Book book = bookRepository
                .findByIdAndUser_Id(
                        id,
                        user.getId()
                )
                .orElseThrow();



        bookRepository.delete(book);
    }

}