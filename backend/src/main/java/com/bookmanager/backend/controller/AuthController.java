package com.bookmanager.backend.controller;

import com.bookmanager.backend.dto.LoginRequest;
import com.bookmanager.backend.dto.LoginResponse;
import com.bookmanager.backend.dto.RegisterRequest;
import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;
import com.bookmanager.backend.config.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }



    /**
     * Cadastro de usuário
     *
     * POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {


        if (userRepository.existsByEmail(request.getEmail())) {

            return ResponseEntity
                    .badRequest()
                    .body("Email já cadastrado");
        }



        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        userRepository.save(user);



        String token =
                jwtService.generateToken(user);



        return ResponseEntity.ok(
                new LoginResponse(
                        token
                )
        );
    }





    /**
     * Login
     *
     * POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {


        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );



        User user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow();



        String token =
                jwtService.generateToken(user);



        return ResponseEntity.ok(
                new LoginResponse(
                        token
                )
        );
    }
}