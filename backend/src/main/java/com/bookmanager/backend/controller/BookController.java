package com.bookmanager.backend.controller;

import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import jakarta.validation.Valid;

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
                    responseCode = "400",
                    description = "Dados inválidos"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @PostMapping
    public ResponseEntity<BookResponse> save(

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do livro",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookRequest.class),
                            examples = @ExampleObject(
                                    name = "Livro",
                                    value = """
                                    {
                                      "title": "Clean Code",
                                      "author": "Robert C. Martin",
                                      "year": 2008,
                                      "description": "Livro sobre boas práticas de programação."
                                    }
                                    """
                            )
                    )
            )
            @Valid
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
            description =
                    """
                    Retorna os livros do usuário autenticado.

                    Permite filtro opcional pelo título.

                    Suporta paginação.

                    Exemplos:

                    /books?page=0&size=10

                    /books?title=java&page=0&size=5

                    /books?page=0&size=10&sort=title,asc
                    """
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Lista retornada com sucesso"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )

    })
    @GetMapping
    public ResponseEntity<Page<BookResponse>> findAll(

            @Parameter(
                    description = "Filtro opcional pelo título",
                    example = "Clean Code"
            )
            @RequestParam(required = false)
            String title,

            @ParameterObject
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "title"
            )
            Pageable pageable,

            Authentication authentication

    ) {

        Page<BookResponse> books =
                bookService.findAll(
                        authentication.getName(),
                        title,
                        pageable
                );

        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza um livro pertencente ao usuário autenticado."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Livro atualizado com sucesso"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
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

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do livro",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "title": "Clean Architecture",
                                      "author": "Robert C. Martin",
                                      "year": 2017,
                                      "description": "Livro atualizado."
                                    }
                                    """
                            )
                    )
            )
            @Valid
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
            description = "Retorna um livro pertencente ao usuário autenticado."
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