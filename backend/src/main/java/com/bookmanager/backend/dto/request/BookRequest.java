package com.bookmanager.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {


    @NotBlank(message = "Title is required")
    private String title;


    @NotBlank(message = "Author is required")
    private String author;


    private Integer year;


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