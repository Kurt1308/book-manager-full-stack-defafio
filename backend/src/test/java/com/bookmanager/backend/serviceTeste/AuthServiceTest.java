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


        user = new User(
                "Lucas Dias",
                "lucas@email.com",
                "123456"
        );


        user.setId(1L);

    }






    @Test
    void register_DeveCadastrarUsuario() {



        RegisterRequest request =
                new RegisterRequest(
                        "Lucas Dias",
                        "lucas@email.com",
                        "123456"
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





        AuthenticationResponse response =
                authService.register(request);





        assertNotNull(response);



        assertEquals(
                "jwt-token",
                response.getToken()
        );



        verify(userRepository)
                .save(
                        any(User.class)
                );



        verify(jwtService)
                .generateToken(
                        any(User.class)
                );

    }









    @Test
    void login_DeveRetornarTokenQuandoUsuarioExiste() {



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






        AuthenticationResponse response =
                authService.login(request);





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

    }









    @Test
    void login_DeveLancarExcecaoQuandoUsuarioNaoExiste() {



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






        assertThrows(
                RuntimeException.class,
                () ->
                        authService.login(request)
        );





        verify(userRepository)
                .findByEmail(
                        request.getEmail()
                );

    }

}