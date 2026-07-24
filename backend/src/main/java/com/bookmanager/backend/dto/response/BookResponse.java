package com.bookmanager.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BookResponse {


    private Long id;

    private String title;

    private String author;

    private Integer year;

}