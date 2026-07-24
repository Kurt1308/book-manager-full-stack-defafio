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


        BookRequest request =
                new BookRequest(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Livro sobre boas práticas"
                );



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

    }





    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldListBooks() throws Exception {



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




        mockMvc.perform(
                get("/books")
        )

        .andExpect(status().isOk())

        .andExpect(
                jsonPath("$.content[0].title")
                        .value("Clean Code")
        );

    }





    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldFindBookById() throws Exception {



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

    }





    @Test
    @WithMockUser(username = "teste@email.com")
    void shouldDeleteBook() throws Exception {



        doNothing()
                .when(bookService)
                .delete(
                        1L,
                        "teste@email.com"
                );



        mockMvc.perform(
                delete("/books/1")
        )

        .andExpect(status().isOk());

    }

}