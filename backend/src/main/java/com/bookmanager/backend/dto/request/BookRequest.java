package com.bookmanager.backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "BookRequest",
        description = "Objeto utilizado para criação e atualização de livros"
)
public class BookRequest {

    @Schema(
            description = "Título do livro",
            example = "Clean Code",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(
            description = "Nome do autor",
            example = "Robert C. Martin",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Author is required")
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

    public BookRequest() {
    }

    public BookRequest(
            String title,
            String author,
            Integer year,
            String description
    ) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}