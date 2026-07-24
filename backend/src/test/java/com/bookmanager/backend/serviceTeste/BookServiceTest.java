package com.bookmanager.backend.serviceTeste;


import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.model.Book;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.BookRepository;
import com.bookmanager.backend.repository.UserRepository;
import com.bookmanager.backend.service.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



class BookServiceTest {



    @Mock
    private BookRepository bookRepository;



    @Mock
    private UserRepository userRepository;



    @InjectMocks
    private BookService bookService;



    private User user;

    private Book book;



    @BeforeEach
    void setup() {


        MockitoAnnotations.openMocks(this);



        user = new User(
                "Lucas",
                "lucas@email.com",
                "123456"
        );



        ReflectionTestUtils.setField(
                user,
                "id",
                1L
        );



        book = new Book(
                "Clean Code",
                "Robert C. Martin",
                2008,
                "Livro sobre boas práticas de programação",
                user
        );



        ReflectionTestUtils.setField(
                book,
                "id",
                10L
        );

    }






    @Test
    void shouldCreateBookSuccessfully() {


        BookRequest request =
                new BookRequest(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(Optional.of(user));



        when(
                bookRepository.save(any(Book.class))
        )
        .thenReturn(book);




        BookResponse response =
                bookService.save(
                        request,
                        "lucas@email.com"
                );



        assertNotNull(response);

        assertEquals(
                "Clean Code",
                response.getTitle()
        );


        assertEquals(
                "Robert C. Martin",
                response.getAuthor()
        );


        assertEquals(
                2008,
                response.getYear()
        );


        verify(
                bookRepository,
                times(1)
        )
        .save(any(Book.class));

    }









    @Test
    void shouldReturnBooksByUser() {


        Pageable pageable =
                PageRequest.of(
                        0,
                        10
                );



        Page<Book> page =
                new PageImpl<>(
                        List.of(book)
                );



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(Optional.of(user));



        when(
                bookRepository.findByUser_Id(
                        1L,
                        pageable
                )
        )
        .thenReturn(page);




        Page<BookResponse> response =
                bookService.findAll(
                        "lucas@email.com",
                        null,
                        pageable
                );



        assertEquals(
                1,
                response.getTotalElements()
        );


        assertEquals(
                "Clean Code",
                response.getContent()
                        .get(0)
                        .getTitle()
        );

    }









    @Test
    void shouldReturnBooksFilteredByTitle() {



        Pageable pageable =
                PageRequest.of(
                        0,
                        10
                );



        Page<Book> page =
                new PageImpl<>(
                        List.of(book)
                );



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(Optional.of(user));



        when(
                bookRepository.findByUser_IdAndTitleContainingIgnoreCase(
                        1L,
                        "Clean",
                        pageable
                )
        )
        .thenReturn(page);




        Page<BookResponse> response =
                bookService.findAll(
                        "lucas@email.com",
                        "Clean",
                        pageable
                );



        assertEquals(
                1,
                response.getTotalElements()
        );


        verify(
                bookRepository
        )
        .findByUser_IdAndTitleContainingIgnoreCase(
                1L,
                "Clean",
                pageable
        );

    }









    @Test
    void shouldFindBookByIdSuccessfully() {



        when(
                bookRepository.findByIdAndUser_Email(
                        10L,
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.of(book)
        );




        BookResponse response =
                bookService.findById(
                        10L,
                        "lucas@email.com"
                );



        assertEquals(
                10L,
                response.getId()
        );


        assertEquals(
                "Clean Code",
                response.getTitle()
        );


    }









    @Test
    void shouldThrowExceptionWhenBookNotFound() {



        when(
                bookRepository.findByIdAndUser_Email(
                        99L,
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.empty()
        );




        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () ->
                                bookService.findById(
                                        99L,
                                        "lucas@email.com"
                                )
                );



        assertEquals(
                "Livro não encontrado",
                exception.getMessage()
        );

    }









    @Test
    void shouldUpdateBookSuccessfully() {



        BookRequest request =
                new BookRequest(
                        "Effective Java",
                        "Joshua Bloch",
                        2018,
                        "Livro Java avançado"
                );



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.of(user)
        );



        when(
                bookRepository.findByIdAndUser_Id(
                        10L,
                        1L
                )
        )
        .thenReturn(
                Optional.of(book)
        );



        when(
                bookRepository.save(any(Book.class))
        )
        .thenReturn(book);




        BookResponse response =
                bookService.update(
                        10L,
                        request,
                        "lucas@email.com"
                );



        assertEquals(
                "Effective Java",
                response.getTitle()
        );



        verify(
                bookRepository
        )
        .save(book);

    }









    @Test
    void shouldDeleteBookSuccessfully() {



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.of(user)
        );



        when(
                bookRepository.findByIdAndUser_Id(
                        10L,
                        1L
                )
        )
        .thenReturn(
                Optional.of(book)
        );




        bookService.delete(
                10L,
                "lucas@email.com"
        );



        verify(
                bookRepository,
                times(1)
        )
        .delete(book);

    }









    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {



        when(
                userRepository.findByEmail(
                        "erro@email.com"
                )
        )
        .thenReturn(
                Optional.empty()
        );



        assertThrows(
                RuntimeException.class,
                () ->
                        bookService.save(
                                new BookRequest(
                                        "Livro",
                                        "Autor",
                                        2024,
                                        "Teste"
                                ),
                                "erro@email.com"
                        )
        );

    }

}