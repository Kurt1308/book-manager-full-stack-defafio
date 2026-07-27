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
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
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

System.out.println("\n==============BookServiceTest=================");
        System.out.println("\n==============================");
        System.out.println("[SETUP] Preparando dados de teste");
        System.out.println("==============================");



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



        System.out.println(
                "[SETUP] Usuário criado ID: "
                + user.getId()
        );

        System.out.println(
                "[SETUP] Email: "
                + user.getEmail()
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



        System.out.println(
                "[SETUP] Livro criado ID: "
                + book.getId()
        );

        System.out.println(
                "[SETUP] Título: "
                + book.getTitle()
        );

    }







    @Test
    void shouldCreateBookSuccessfully() {

System.out.println("\n==============BookServiceTest=================");
        System.out.println("\n==============================");
        System.out.println("TESTE: Criar livro");
        System.out.println("==============================");



        BookRequest request =
                new BookRequest(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



        System.out.println("[DEBUG] Request criado");
        System.out.println(
                "[DEBUG] Título: "
                + request.getTitle()
        );



        when(
                userRepository.findByEmail(
                        "lucas@email.com"
                )
        )
        .thenReturn(Optional.of(user));



        System.out.println(
                "[DEBUG] Mock UserRepository configurado"
        );



        when(
                bookRepository.save(any(Book.class))
        )
        .thenReturn(book);



        System.out.println(
                "[DEBUG] Mock BookRepository.save configurado"
        );



        BookResponse response =
                bookService.save(
                        request,
                        "lucas@email.com"
                );



        System.out.println(
                "[DEBUG] Livro retornado: "
                + response.getTitle()
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



        System.out.println(
                "[RESULTADO] Livro criado com sucesso"
        );

    }







    @Test
    void shouldReturnBooksByUser() {

System.out.println("\n==============BookServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Listar livros do usuário");
        System.out.println("==============================");



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



        System.out.println(
                "[DEBUG] Mock busca por usuário configurado"
        );



        Page<BookResponse> response =
                bookService.findAll(
                        "lucas@email.com",
                        null,
                        pageable
                );



        System.out.println(
                "[DEBUG] Quantidade encontrada: "
                + response.getTotalElements()
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



        System.out.println(
                "[RESULTADO] Livros encontrados com sucesso"
        );

    }







    @Test
    void shouldReturnBooksFilteredByTitle() {

System.out.println("\n==============BookServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Buscar livros por título");
        System.out.println("==============================");



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



        System.out.println(
                "[DEBUG] Pesquisa: Clean"
        );



        Page<BookResponse> response =
                bookService.findAll(
                        "lucas@email.com",
                        "Clean",
                        pageable
                );



        System.out.println(
                "[DEBUG] Resultado: "
                + response.getTotalElements()
                + " livro(s)"
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



        System.out.println(
                "[RESULTADO] Filtro por título funcionando"
        );

    }







    @Test
    void shouldFindBookByIdSuccessfully() {
System.out.println("\n==============BookServiceTest=================");


        System.out.println("\n==============================");
        System.out.println("TESTE: Buscar livro por ID");
        System.out.println("==============================");



        when(
                bookRepository.findByIdAndUser_Email(
                        10L,
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.of(book)
        );



        System.out.println(
                "[DEBUG] Buscando livro ID: 10"
        );



        BookResponse response =
                bookService.findById(
                        10L,
                        "lucas@email.com"
                );



        System.out.println(
                "[DEBUG] Livro encontrado: "
                + response.getTitle()
        );



        assertEquals(
                10L,
                response.getId()
        );



        assertEquals(
                "Clean Code",
                response.getTitle()
        );



        System.out.println(
                "[RESULTADO] Livro encontrado com sucesso"
        );

    }







    @Test
    void shouldThrowExceptionWhenBookNotFound() {

System.out.println("\n==============BookServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Livro inexistente");
        System.out.println("==============================");



        when(
                bookRepository.findByIdAndUser_Email(
                        99L,
                        "lucas@email.com"
                )
        )
        .thenReturn(
                Optional.empty()
        );



        System.out.println(
                "[DEBUG] Buscando livro ID: 99"
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



        System.out.println(
                "[DEBUG] Exceção recebida: "
                + exception.getMessage()
        );



        assertEquals(
                "Livro não encontrado",
                exception.getMessage()
        );



        System.out.println(
                "[RESULTADO] Exceção validada corretamente"
        );

    }







    @Test
    void shouldUpdateBookSuccessfully() {

System.out.println("\n==============BookServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Atualizar livro");
        System.out.println("==============================");



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



        System.out.println(
                "[DEBUG] Atualizando livro ID: 10"
        );



        BookResponse response =
                bookService.update(
                        10L,
                        request,
                        "lucas@email.com"
                );



        System.out.println(
                "[DEBUG] Novo título: "
                + response.getTitle()
        );



        assertEquals(
                "Effective Java",
                response.getTitle()
        );



        verify(
                bookRepository
        )
        .save(book);



        System.out.println(
                "[RESULTADO] Livro atualizado com sucesso"
        );

    }







    @Test
    void shouldDeleteBookSuccessfully() {

System.out.println("\n==============BookServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Deletar livro");
        System.out.println("==============================");



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



        System.out.println(
                "[DEBUG] Removendo livro ID: 10"
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



        System.out.println(
                "[RESULTADO] Livro removido com sucesso"
        );

    }







    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {


System.out.println("\n==============BookServiceTest=================");
        System.out.println("\n==============================");
        System.out.println("TESTE: Usuário inexistente");
        System.out.println("==============================");



        when(
                userRepository.findByEmail(
                        "erro@email.com"
                )
        )
        .thenReturn(
                Optional.empty()
        );



        System.out.println(
                "[DEBUG] Tentando criar livro com usuário inexistente"
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



        System.out.println(
                "[RESULTADO] Exceção lançada corretamente"
        );

    }

}