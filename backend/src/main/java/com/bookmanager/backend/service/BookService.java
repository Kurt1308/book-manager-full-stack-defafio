package com.bookmanager.backend.service;

import com.bookmanager.backend.dto.request.BookRequest;
import com.bookmanager.backend.dto.response.BookResponse;
import com.bookmanager.backend.config.exception.ResourceNotFoundException;
import com.bookmanager.backend.model.Book;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.BookRepository;
import com.bookmanager.backend.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        System.out.println("[DEBUG] BookService inicializado");
    }


    public BookResponse save(
            BookRequest request,
            String email
    ) {

        System.out.println("=================BookService====================");
        System.out.println("[DEBUG] Iniciando criação de livro");
        System.out.println("[DEBUG] Usuário autenticado: " + email);

        System.out.println("[DEBUG] Dados recebidos:");
        System.out.println("Título: " + request.getTitle());
        System.out.println("Autor: " + request.getAuthor());
        System.out.println("Ano: " + request.getYear());


        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {

                    System.out.println("[DEBUG] Usuário não encontrado: " + email);

                    return new ResourceNotFoundException(
                            "Usuário não encontrado"
                    );
                });


        System.out.println(
                "[DEBUG] Usuário encontrado ID: "
                + user.getId()
        );


        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setYear(request.getYear());
        book.setDescription(request.getDescription());
        book.setUser(user);


        System.out.println("[DEBUG] Salvando livro no banco");


        Book saved = bookRepository.save(book);


        System.out.println(
                "[DEBUG] Livro criado com sucesso ID: "
                + saved.getId()
        );


        return new BookResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor(),
                saved.getYear(),
                saved.getDescription()
        );
    }



    public Page<BookResponse> findAll(
            String email,
            String title,
            Pageable pageable
    ) {
System.out.println("=================BookService====================");
        System.out.println("[DEBUG] Buscando livros");
        System.out.println("[DEBUG] Usuário: " + email);
        System.out.println("[DEBUG] Filtro título: " + title);


        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Usuário não encontrado: "
                            + email
                    );

                    return new ResourceNotFoundException(
                            "Usuário não encontrado"
                    );
                });


        Page<Book> books;


        if (title != null && !title.isBlank()) {

            System.out.println(
                    "[DEBUG] Executando busca por título"
            );


            books = bookRepository
                    .findByUser_IdAndTitleContainingIgnoreCase(
                            user.getId(),
                            title,
                            pageable
                    );


        } else {

            System.out.println(
                    "[DEBUG] Buscando todos os livros do usuário"
            );


            books = bookRepository
                    .findByUser_Id(
                            user.getId(),
                            pageable
                    );
        }


        System.out.println(
                "[DEBUG] Quantidade encontrada: "
                + books.getTotalElements()
        );


        return books.map(book -> {

            System.out.println(
                    "[DEBUG] Convertendo livro ID: "
                    + book.getId()
            );

            return new BookResponse(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getDescription()
            );
        });

    }




    public BookResponse findById(
            Long id,
            String email
    ) {

System.out.println("=================BookService====================");
        System.out.println("[DEBUG] Buscando livro por ID");
        System.out.println("[DEBUG] ID informado: " + id);
        System.out.println("[DEBUG] Usuário: " + email);



        Book book = bookRepository
                .findByIdAndUser_Email(
                        id,
                        email
                )
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Livro não encontrado ID: "
                            + id
                    );

                    return new ResourceNotFoundException(
                            "Livro não encontrado"
                    );
                });



        System.out.println(
                "[DEBUG] Livro encontrado: "
                + book.getTitle()
        );


        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getDescription()
        );

    }





    public BookResponse update(
            Long id,
            BookRequest dto,
            String email
    ) {

System.out.println("=================BookService====================");
        System.out.println("[DEBUG] Atualizando livro");
        System.out.println("[DEBUG] ID: " + id);
        System.out.println("[DEBUG] Usuário: " + email);



        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Usuário não encontrado"
                    );

                    return new ResourceNotFoundException(
                            "Usuário não encontrado"
                    );
                });



        Book book = bookRepository
                .findByIdAndUser_Id(
                        id,
                        user.getId()
                )
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Livro não encontrado para atualização"
                    );

                    return new ResourceNotFoundException(
                            "Livro não encontrado"
                    );
                });



        System.out.println(
                "[DEBUG] Livro antigo: "
                + book.getTitle()
        );



        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        book.setDescription(dto.getDescription());



        Book updated = bookRepository.save(book);



        System.out.println(
                "[DEBUG] Livro atualizado ID: "
                + updated.getId()
        );



        return new BookResponse(
                updated.getId(),
                updated.getTitle(),
                updated.getAuthor(),
                updated.getYear(),
                updated.getDescription()
        );

    }





    public void delete(
            Long id,
            String email
    ) {

System.out.println("=================BookService====================");
        System.out.println("[DEBUG] Excluindo livro");
        System.out.println("[DEBUG] ID: " + id);
        System.out.println("[DEBUG] Usuário: " + email);



        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Usuário não encontrado"
                    );

                    return new ResourceNotFoundException(
                            "Usuário não encontrado"
                    );
                });



        Book book = bookRepository
                .findByIdAndUser_Id(
                        id,
                        user.getId()
                )
                .orElseThrow(() -> {

                    System.out.println(
                            "[DEBUG] Livro não encontrado para exclusão"
                    );

                    return new ResourceNotFoundException(
                            "Livro não encontrado"
                    );
                });



        System.out.println(
                "[DEBUG] Removendo livro: "
                + book.getTitle()
        );


        bookRepository.delete(book);


        System.out.println(
                "[DEBUG] Livro removido com sucesso"
        );

    }

}