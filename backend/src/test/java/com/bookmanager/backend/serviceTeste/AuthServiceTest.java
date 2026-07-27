package com.bookmanager.backend.serviceTeste;


import com.bookmanager.backend.config.jwt.JwtService;
import com.bookmanager.backend.dto.request.LoginRequest;
import com.bookmanager.backend.dto.request.RegisterRequest;
import com.bookmanager.backend.dto.response.AuthenticationResponse;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;
import com.bookmanager.backend.service.AuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class AuthServiceTest {


    @Mock
    private UserRepository userRepository;


    @Mock
    private PasswordEncoder passwordEncoder;


    @Mock
    private JwtService jwtService;


    @Mock
    private AuthenticationManager authenticationManager;


    @InjectMocks
    private AuthService authService;



    private User user;



    @BeforeEach
    void setup() {

        System.out.println("\n==============AuthServiceTest=================");
        System.out.println("\n[SETUP] Criando usuário de teste");


        user = new User(
                "Lucas Dias",
                "lucas@email.com",
                "123456"
        );


        user.setId(1L);


        System.out.println(
                "[SETUP] Usuário criado ID: "
                + user.getId()
        );

    }







    @Test
    void register_DeveCadastrarUsuario() {

System.out.println("\n==============AuthServiceTest=================");
        System.out.println("\n==============================");
        System.out.println("TESTE: Cadastro de usuário");
        System.out.println("==============================");


        RegisterRequest request =
                new RegisterRequest(
                        "Lucas Dias",
                        "lucas@email.com",
                        "123456"
                );


        System.out.println(
                "[DEBUG] Nome: "
                + request.getName()
        );

        System.out.println(
                "[DEBUG] Email: "
                + request.getEmail()
        );



        when(passwordEncoder.encode(
                request.getPassword()
        ))
        .thenReturn("encrypted-password");



        when(userRepository.save(
                any(User.class)
        ))
        .thenAnswer(
                invocation -> invocation.getArgument(0)
        );



        when(jwtService.generateToken(
                any(User.class)
        ))
        .thenReturn("jwt-token");



        System.out.println("[DEBUG] Mocks configurados");


        AuthenticationResponse response =
                authService.register(request);



        System.out.println("[DEBUG] Resposta recebida");
        System.out.println(
                "[DEBUG] Token: "
                + response.getToken()
        );



        assertNotNull(response);


        assertEquals(
                "jwt-token",
                response.getToken()
        );


        verify(userRepository)
                .save(any(User.class));


        verify(jwtService)
                .generateToken(any(User.class));



        System.out.println(
                "[RESULTADO] Cadastro realizado com sucesso"
        );

    }







    @Test
    void login_DeveRetornarTokenQuandoUsuarioExiste() {
System.out.println("\n==============AuthServiceTest=================");

        System.out.println("\n==============================");
        System.out.println("TESTE: Login usuário existente");
        System.out.println("==============================");



        LoginRequest request =
                new LoginRequest(
                        "lucas@email.com",
                        "123456"
                );



        when(userRepository.findByEmail(
                request.getEmail()
        ))
        .thenReturn(
                Optional.of(user)
        );



        when(jwtService.generateToken(
                any(User.class)
        ))
        .thenReturn(
                "jwt-token"
        );



        System.out.println(
                "[DEBUG] Buscando usuário: "
                + request.getEmail()
        );



        AuthenticationResponse response =
                authService.login(request);



        System.out.println(
                "[DEBUG] Token gerado: "
                + response.getToken()
        );



        assertNotNull(response);


        assertEquals(
                "jwt-token",
                response.getToken()
        );



        verify(userRepository)
                .findByEmail(
                        request.getEmail()
                );


        verify(jwtService)
                .generateToken(
                        any(User.class)
                );



        System.out.println(
                "[RESULTADO] Login realizado com sucesso"
        );

    }









    @Test
    void login_DeveLancarExcecaoQuandoUsuarioNaoExiste() {

System.out.println("\n==============AuthServiceTest=================");
        System.out.println("\n==============================");
        System.out.println("TESTE: Login usuário inexistente");
        System.out.println("==============================");



        LoginRequest request =
                new LoginRequest(
                        "naoexiste@email.com",
                        "123456"
                );



        when(userRepository.findByEmail(
                request.getEmail()
        ))
        .thenReturn(
                Optional.empty()
        );



        System.out.println(
                "[DEBUG] Tentando login: "
                + request.getEmail()
        );



        assertThrows(
                RuntimeException.class,
                () ->
                        authService.login(request)
        );



        System.out.println(
                "[RESULTADO] Exceção lançada corretamente"
        );



        verify(userRepository)
                .findByEmail(
                        request.getEmail()
                );

    }

}