package com.bookmanager.backend.controller;


import com.bookmanager.backend.dto.response.AuthenticationResponse;
import com.bookmanager.backend.dto.request.LoginRequest;
import com.bookmanager.backend.dto.request.RegisterRequest;
import com.bookmanager.backend.service.AuthService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import jakarta.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {



    private final AuthService authService;



    public AuthController(
            AuthService authService
    ) {

        this.authService = authService;
    }






    @Operation(
            summary = "Cadastrar novo usuário",
            description = "Cria um usuário e retorna um JWT para autenticação"
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário criado com sucesso"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Email já cadastrado"
            )

    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(


            @Valid
            @RequestBody RegisterRequest request

    ) {


        return ResponseEntity.ok(
                authService.register(request)
        );
    }









    @Operation(
            summary = "Realizar login",
            description = "Autentica usuário e retorna JWT"
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas"
            )

    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(


            @Valid
            @RequestBody LoginRequest request

    ) {


        return ResponseEntity.ok(
                authService.login(request)
        );
    }

}