package com.bookmanager.backend.repository;

import com.bookmanager.backend.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByUser_Id(Long userId);



    Optional<Book> findByIdAndUser_Id(
            Long id,
            Long userId
    );



    Optional<Book> findByIdAndUser_Email(
            Long id,
            String email
    );

}