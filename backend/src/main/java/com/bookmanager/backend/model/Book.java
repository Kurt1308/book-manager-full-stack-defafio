package com.bookmanager.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String author;


    @Column(nullable = true)
    private Integer year;


    @Column(nullable = true, length = 1000)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Book() {
    }



    public Book(
            String title,
            String author,
            Integer year,
            String description,
            User user
    ) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.user = user;
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


    public String getDescription() {
        return description;
    }


    public User getUser() {
        return user;
    }



    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public void setYear(Integer year) {
        this.year = year;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setUser(User user) {
        this.user = user;
    }
}