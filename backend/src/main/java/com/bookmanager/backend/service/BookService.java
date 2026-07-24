        package com.bookmanager.backend.service;

        import com.bookmanager.backend.dto.response.BookResponse;
        import com.bookmanager.backend.model.Book;
        import com.bookmanager.backend.model.User;
        import com.bookmanager.backend.repository.BookRepository;
        import com.bookmanager.backend.repository.UserRepository;
        import com.bookmanager.backend.dto.request.BookRequest;

        import org.springframework.stereotype.Service;

        import java.util.List;


        @Service
        public class BookService {


        private final BookRepository bookRepository;

        private final UserRepository userRepository;



        public BookService(
                BookRepository bookRepository,
                UserRepository userRepository
        ) {
                this.bookRepository = bookRepository;
                this.userRepository = userRepository;
        }




        public BookResponse save(
                BookRequest request,
                String email
        ) {


        User user = userRepository
                .findByEmail(email)
                .orElseThrow();



        Book book = new Book();


        book.setTitle(
                request.getTitle()
        );


        book.setAuthor(
                request.getAuthor()
        );


        book.setYear(
                request.getYear()
        );


        book.setUser(user);



        Book saved = bookRepository.save(book);



        return new BookResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor(),
                saved.getYear()
        );
        }





        public List<BookResponse> findAll(
                String email
        ) {


                User user = userRepository
                        .findByEmail(email)
                        .orElseThrow();



                return bookRepository
                        .findByUser_Id(user.getId())
                        .stream()
                        .map(book -> new BookResponse(
                                book.getId(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getYear()
                        ))
                        .toList();
        }





        public BookResponse update(
                Long id,
                BookRequest dto,
                String email
        ) {


                User user = userRepository
                        .findByEmail(email)
                        .orElseThrow();



                Book book = bookRepository
                        .findByIdAndUser_Id(
                                id,
                                user.getId()
                        )
                        .orElseThrow();



                book.setTitle(
                        dto.getTitle()
                );


                book.setAuthor(
                        dto.getAuthor()
                );


                book.setYear(
                        dto.getYear()
                );



                Book updated = bookRepository.save(book);



                return new BookResponse(
                        updated.getId(),
                        updated.getTitle(),
                        updated.getAuthor(),
                        updated.getYear()
                );
        }





        public void delete(
                Long id,
                String email
        ) {


                User user = userRepository
                        .findByEmail(email)
                        .orElseThrow();



                Book book = bookRepository
                        .findByIdAndUser_Id(
                                id,
                                user.getId()
                        )
                        .orElseThrow();



                bookRepository.delete(book);
        }
        }