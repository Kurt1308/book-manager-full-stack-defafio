package com.bookmanager.backend.dto.response;

public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private Integer year;


    public BookResponse(
            Long id,
            String title,
            String author,
            Integer year
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public Integer getYear() {
        return year;
    }
}