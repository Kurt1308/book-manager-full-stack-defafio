package com.bookmanager.backend.dto.request;


public class BookRequest {


    private String title;


    private String author;


    private Integer year;


    private String description;



    public BookRequest() {
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