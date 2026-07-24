package com.bookmanager.backend.controller;

import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Books",
        description = "Endpoints para gerenciamento de livros"
)
public class BookController {


    private final BookService bookService;


    public BookController(
            BookService bookService
    ) {
        this.bookService = bookService;
    }



    @Operation(
            summary = "Criar um novo livro",
            description = "Cadastra um novo livro associado ao usuário autenticado."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Livro criado com sucesso"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
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




    @Operation(
            summary = "Listar livros",
            description = "Retorna todos os livros do usuário autenticado. Permite busca opcional pelo título."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de livros retornada com sucesso"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(

            @Parameter(
                    description = "Filtro opcional pelo título do livro",
                    example = "Clean Code"
            )
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





    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza os dados de um livro pertencente ao usuário autenticado."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Livro atualizado com sucesso"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Livro não encontrado"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(

            @Parameter(
                    description = "ID do livro",
                    example = "1"
            )
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





    @Operation(
            summary = "Excluir livro",
            description = "Remove um livro pertencente ao usuário autenticado."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Livro removido com sucesso"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Livro não encontrado"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(

            @Parameter(
                    description = "ID do livro",
                    example = "1"
            )
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





    @Operation(
            summary = "Buscar livro por ID",
            description = "Retorna os detalhes de um livro específico pertencente ao usuário autenticado."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Livro encontrado"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Livro não encontrado"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(

            @Parameter(
                    description = "ID do livro",
                    example = "1"
            )
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