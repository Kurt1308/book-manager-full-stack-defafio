package com.bookmanager.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String author;


    @Column(nullable = false)
    private Integer year;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Book(
            String title,
            String author,
            Integer year,
            User user
    ) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.user = user;
    }

}