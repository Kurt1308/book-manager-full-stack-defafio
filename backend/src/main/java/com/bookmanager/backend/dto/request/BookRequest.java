package com.bookmanager.backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BookRequest {


    private String title;

    private String author;

    private Integer year;

}