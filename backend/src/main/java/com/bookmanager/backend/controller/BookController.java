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


        System.out.println(
                "[BOOK CONTROLLER] Inicializado"
        );

    }









    @Operation(
            summary = "Criar um novo livro",
            description =
                    "Cadastra um novo livro associado ao usuário autenticado."
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
                            schema = @Schema(
                                    implementation = BookRequest.class
                            ),
                            examples = @ExampleObject(
                                    name = "Livro",
                                    value =
                                            """
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



        System.out.println(
                "==================BookController===================="
        );


        System.out.println(
                "[CREATE BOOK] Requisição recebida"
        );



        String email =
                authentication.getName();



        System.out.println(
                "[CREATE BOOK] Usuário autenticado: "
                        + email
        );



        System.out.println(
                "[CREATE BOOK] Dados recebidos:"
        );


        System.out.println(
                "Título: "
                        + dto.getTitle()
        );


        System.out.println(
                "Autor: "
                        + dto.getAuthor()
        );


        System.out.println(
                "Ano: "
                        + dto.getYear()
        );


        System.out.println(
                "Descrição: "
                        + dto.getDescription()
        );





        BookResponse response =
                bookService.save(
                        dto,
                        email
                );





        System.out.println(
                "[CREATE BOOK] Livro criado com sucesso"
        );


        System.out.println(
                "[CREATE BOOK] ID gerado: "
                        + response.getId()
        );



        System.out.println(
                "===================BookController==================="
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



        System.out.println(
                "===================BookController==================="
        );


        System.out.println(
                "[LIST BOOKS] Consulta iniciada"
        );



        String email =
                authentication.getName();



        System.out.println(
                "[LIST BOOKS] Usuário autenticado: "
                        + email
        );



        System.out.println(
                "[LIST BOOKS] Filtro título: "
                        + title
        );



        System.out.println(
                "[LIST BOOKS] Paginação:"
        );


        System.out.println(
                "Página: "
                        + pageable.getPageNumber()
        );


        System.out.println(
                "Tamanho: "
                        + pageable.getPageSize()
        );


        System.out.println(
                "Ordenação: "
                        + pageable.getSort()
        );






        Page<BookResponse> books =
                bookService.findAll(
                        email,
                        title,
                        pageable
                );






        System.out.println(
                "[LIST BOOKS] Consulta finalizada"
        );


        System.out.println(
                "[LIST BOOKS] Total encontrados: "
                        + books.getTotalElements()
        );



        System.out.println(
                "=====================BookController================="
        );



        return ResponseEntity.ok(books);

    }












    @Operation(
            summary = "Atualizar livro",
            description =
                    "Atualiza um livro pertencente ao usuário autenticado."
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
                            schema = @Schema(
                                    implementation = BookRequest.class
                            ),
                            examples = @ExampleObject(
                                    value =
                                            """
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



        System.out.println(
                "===================BookController==================="
        );


        System.out.println(
                "[UPDATE BOOK] Atualização solicitada"
        );



        String email =
                authentication.getName();



        System.out.println(
                "[UPDATE BOOK] ID recebido: "
                        + id
        );



        System.out.println(
                "[UPDATE BOOK] Usuário autenticado: "
                        + email
        );



        System.out.println(
                "[UPDATE BOOK] Novos dados:"
        );



        System.out.println(
                "Título: "
                        + dto.getTitle()
        );



        System.out.println(
                "Autor: "
                        + dto.getAuthor()
        );



        System.out.println(
                "Ano: "
                        + dto.getYear()
        );



        System.out.println(
                "Descrição: "
                        + dto.getDescription()
        );







        BookResponse response =
                bookService.update(
                        id,
                        dto,
                        email
                );







        System.out.println(
                "[UPDATE BOOK] Livro atualizado com sucesso"
        );


        System.out.println(
                "[UPDATE BOOK] ID atualizado: "
                        + response.getId()
        );



        System.out.println(
                "===================BookController==================="
        );



        return ResponseEntity.ok(response);

    }

        @Operation(
            summary = "Excluir livro",
            description =
                    "Remove um livro pertencente ao usuário autenticado."
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



        System.out.println(
                "================BookController======================"
        );


        System.out.println(
                "[DELETE BOOK] Exclusão solicitada"
        );



        String email =
                authentication.getName();




        System.out.println(
                "[DELETE BOOK] ID recebido: "
                        + id
        );



        System.out.println(
                "[DELETE BOOK] Usuário autenticado: "
                        + email
        );





        bookService.delete(
                id,
                email
        );





        System.out.println(
                "[DELETE BOOK] Livro removido com sucesso"
        );



        System.out.println(
                "===================BookController==================="
        );





        return ResponseEntity.ok(
                "Livro removido com sucesso"
        );

    }













    @Operation(
            summary = "Buscar livro por ID",
            description =
                    "Retorna um livro pertencente ao usuário autenticado."
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



        System.out.println(
                "===================BookController==================="
        );


        System.out.println(
                "[GET BOOK] Busca iniciada"
        );



        String email =
                authentication.getName();




        System.out.println(
                "[GET BOOK] ID pesquisado: "
                        + id
        );



        System.out.println(
                "[GET BOOK] Usuário autenticado: "
                        + email
        );







        BookResponse response =
                bookService.findById(
                        id,
                        email
                );







        System.out.println(
                "[GET BOOK] Livro encontrado"
        );



        System.out.println(
                "[GET BOOK] Título retornado: "
                        + response.getTitle()
        );



        System.out.println(
                "===================BookController==================="
        );





        return ResponseEntity.ok(response);

    }



}