package com.bookmanager.backend.repository;


import com.bookmanager.backend.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface BookRepository extends JpaRepository<Book, Long> {


    Page<Book> findByUser_Id(
            Long userId,
            Pageable pageable
    );



    Page<Book> findByUser_IdAndTitleContainingIgnoreCase(
            Long userId,
            String title,
            Pageable pageable
    );



    Optional<Book> findByIdAndUser_Id(
            Long id,
            Long userId
    );



    Optional<Book> findByIdAndUser_Email(
            Long id,
            String email
    );

}