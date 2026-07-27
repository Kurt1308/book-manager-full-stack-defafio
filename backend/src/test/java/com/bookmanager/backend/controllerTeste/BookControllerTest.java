package com.bookmanager.backend.controllerTeste;


import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.service.BookService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private BookService bookService;


    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldCreateBook() throws Exception {


        System.out.println("\n==============================");
        System.out.println("TESTE: Criar Livro");
        System.out.println("================BookControllerTest==============");


        BookRequest request =
                new BookRequest(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );


        System.out.println("[DEBUG] Dados enviados:");
        System.out.println(request);



        BookResponse response =
                new BookResponse(
                        1L,
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



        when(
                bookService.save(
                        any(BookRequest.class),
                        eq("teste@email.com")
                )
        )
        .thenReturn(response);



        System.out.println("[DEBUG] Mock configurado");
        System.out.println("[DEBUG] Executando POST /books");



        mockMvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(request)
                        )
        )

        .andExpect(status().isOk())

        .andExpect(
                jsonPath("$.title")
                        .value("Clean Code")
        );



        System.out.println("[RESULTADO] Livro criado com sucesso");

    }




    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldListBooks() throws Exception {


        System.out.println("\n==============================");
        System.out.println("TESTE: Listar Livros");
        System.out.println("==============BookControllerTest================");



        BookResponse book =
                new BookResponse(
                        1L,
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



        Page<BookResponse> page =
                new PageImpl<>(
                        List.of(book)
                );



        when(
                bookService.findAll(
                        eq("teste@email.com"),
                        eq(null),
                        any(Pageable.class)
                )
        )
        .thenReturn(page);



        System.out.println("[DEBUG] Mock configurado");
        System.out.println("[DEBUG] Executando GET /books");



        mockMvc.perform(
                get("/books")
        )

        .andExpect(status().isOk())

        .andExpect(
                jsonPath("$.content[0].title")
                        .value("Clean Code")
        );



        System.out.println("[RESULTADO] Livros encontrados com sucesso");

    }





    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldFindBookById() throws Exception {


        System.out.println("\n==============================");
        System.out.println("TESTE: Buscar Livro por ID");
        System.out.println("==============BookControllerTest================");


        BookResponse response =
                new BookResponse(
                        1L,
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



        when(
                bookService.findById(
                        1L,
                        "teste@email.com"
                )
        )
        .thenReturn(response);



        System.out.println("[DEBUG] Buscando ID: 1");
        System.out.println("[DEBUG] Executando GET /books/1");



        mockMvc.perform(
                get("/books/1")
        )

        .andExpect(status().isOk())

        .andExpect(
                jsonPath("$.id")
                        .value(1)
        )

        .andExpect(
                jsonPath("$.title")
                        .value("Clean Code")
        );



        System.out.println("[RESULTADO] Livro encontrado");

    }





    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldDeleteBook() throws Exception {


        System.out.println("\n==============================");
        System.out.println("TESTE: Deletar Livro");
        System.out.println("===============BookControllerTest===============");



        doNothing()
                .when(bookService)
                .delete(
                        1L,
                        "teste@email.com"
                );



        System.out.println("[DEBUG] Mock delete configurado");
        System.out.println("[DEBUG] Executando DELETE /books/1");



        mockMvc.perform(
                delete("/books/1")
        )

        .andExpect(status().isOk());



        System.out.println("[RESULTADO] Livro deletado");

    }

}