package com.bookmanager.backend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "BookResponse",
        description = "Objeto retornado nas operações de consulta de livros"
)
public class BookResponse {

    @Schema(
            description = "Identificador único do livro",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Título do livro",
            example = "Clean Code"
    )
    private String title;

    @Schema(
            description = "Autor do livro",
            example = "Robert C. Martin"
    )
    private String author;

    @Schema(
            description = "Ano de publicação",
            example = "2008"
    )
    private Integer year;

    @Schema(
            description = "Descrição do livro",
            example = "Livro sobre boas práticas de programação."
    )
    private String description;

}