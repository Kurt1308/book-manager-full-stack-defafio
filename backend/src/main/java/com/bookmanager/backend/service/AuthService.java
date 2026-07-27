package com.bookmanager.backend.service;


import com.bookmanager.backend.config.exception.DuplicateResourceException;
import com.bookmanager.backend.config.exception.ResourceNotFoundException;
import com.bookmanager.backend.config.jwt.JwtService;

import com.bookmanager.backend.dto.request.LoginRequest;
import com.bookmanager.backend.dto.request.RegisterRequest;
import com.bookmanager.backend.dto.response.AuthenticationResponse;

import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;



@Service
public class AuthService {



    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;





    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {


        System.out.println(
                "[AUTH SERVICE] Criando AuthService"
        );


        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;



        System.out.println(
                "[AUTH SERVICE] Dependências carregadas"
        );


    }








    public AuthenticationResponse register(
            RegisterRequest request
    ) {



        System.out.println(
                "==================AuthService===================="
        );


        System.out.println(
                "[AUTH SERVICE] Iniciando registro de usuário"
        );


        System.out.println(
                "[AUTH SERVICE] Nome recebido: "
                +
                request.getName()
        );


        System.out.println(
                "[AUTH SERVICE] Email recebido: "
                +
                request.getEmail()
        );



        System.out.println(
                "[AUTH SERVICE] Verificando se email já existe"
        );



        if(userRepository.existsByEmail(request.getEmail())) {



            System.out.println(
                    "[AUTH SERVICE] Email já cadastrado"
            );


            throw new DuplicateResourceException(
                    "Email já cadastrado"
            );

        }



        System.out.println(
                "[AUTH SERVICE] Email disponível"
        );



        System.out.println(
                "[AUTH SERVICE] Criptografando senha"
        );



        String encryptedPassword =
                passwordEncoder.encode(
                        request.getPassword()
                );



        System.out.println(
                "[AUTH SERVICE] Senha criptografada com sucesso"
        );



        User user =
                new User(

                        request.getName(),

                        request.getEmail(),

                        encryptedPassword

                );



        System.out.println(
                "[AUTH SERVICE] Usuário criado em memória"
        );



        userRepository.save(user);



        System.out.println(
                "[AUTH SERVICE] Usuário salvo no banco"
        );


        System.out.println(
                "[AUTH SERVICE] Gerando JWT"
        );



        String token =
                jwtService.generateToken(user);



        System.out.println(
                "[AUTH SERVICE] JWT gerado"
        );



        System.out.println(
                "[AUTH SERVICE] Registro concluído"
        );


        System.out.println(
                "=================AuthService====================="
        );



        return new AuthenticationResponse(token);

    }









    public AuthenticationResponse login(
            LoginRequest request
    ) {



        System.out.println(
                "=================AuthService====================="
        );


        System.out.println(
                "[AUTH SERVICE] Iniciando login"
        );


        System.out.println(
                "[AUTH SERVICE] Email recebido: "
                +
                request.getEmail()
        );



        try {



            System.out.println(
                    "[AUTH SERVICE] Validando credenciais com Spring Security"
            );



            authenticationManager.authenticate(


                    new UsernamePasswordAuthenticationToken(

                            request.getEmail(),

                            request.getPassword()

                    )

            );



            System.out.println(
                    "[AUTH SERVICE] Credenciais válidas"
            );



        }
        catch(Exception exception) {



            System.err.println(
                    "[AUTH SERVICE] Falha na autenticação"
            );


            System.err.println(
                    "[AUTH SERVICE] Motivo: "
                    +
                    exception.getMessage()
            );



            throw new BadCredentialsException(
                    "Email ou senha inválidos"
            );


        }






        System.out.println(
                "[AUTH SERVICE] Buscando usuário pelo email"
        );



        User user =
                userRepository

                .findByEmail(request.getEmail())

                .orElseThrow(() -> {



                    System.err.println(
                            "[AUTH SERVICE] Usuário não encontrado"
                    );


                    return new ResourceNotFoundException(
                            "Email ou senha inválidos"
                    );

                });





        System.out.println(
                "[AUTH SERVICE] Usuário encontrado"
        );


        System.out.println(
                "[AUTH SERVICE] ID usuário: "
                +
                user.getId()
        );



        System.out.println(
                "[AUTH SERVICE] Gerando JWT"
        );



        String token =
                jwtService.generateToken(user);



        System.out.println(
                "[AUTH SERVICE] JWT retornado para cliente"
        );



        System.out.println(
                "===================AuthService==================="
        );



        return new AuthenticationResponse(token);

    }



}