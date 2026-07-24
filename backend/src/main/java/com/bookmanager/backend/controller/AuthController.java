package com.bookmanager.backend.controller;


import com.bookmanager.backend.dto.response.AuthenticationResponse;
import com.bookmanager.backend.dto.request.LoginRequest;
import com.bookmanager.backend.dto.request.RegisterRequest;
import com.bookmanager.backend.service.AuthService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(
        name = "Authentication",
        description = "Endpoints responsáveis por cadastro e autenticação de usuários"
)
public class AuthController {



    private final AuthService authService;



    public AuthController(
            AuthService authService
    ) {

        this.authService = authService;
    }







    @Operation(
            summary = "Cadastrar usuário",
            description =
                    """
                    Cria um novo usuário no sistema.
                    
                    Após o cadastro, um token JWT é gerado 
                    e retornado para autenticação nas rotas protegidas.
                    """
    )
    @ApiResponses(value = {


            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso e JWT gerado"
            ),


            @ApiResponse(
                    responseCode = "400",
                    description =
                            "Dados inválidos ou email já cadastrado"
            )

    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(


            @Valid
            @RequestBody
            RegisterRequest request

    ) {


        AuthenticationResponse response =
                authService.register(request);



        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }









    @Operation(
            summary = "Login do usuário",
            description =
                    """
                    Autentica um usuário utilizando email e senha.
                    
                    Caso as credenciais sejam válidas,
                    retorna um JWT para acesso às rotas protegidas.
                    """
    )
    @ApiResponses(value = {


            @ApiResponse(
                    responseCode = "200",
                    description =
                            "Usuário autenticado e JWT retornado"
            ),


            @ApiResponse(
                    responseCode = "401",
                    description =
                            "Email ou senha inválidos"
            ),


            @ApiResponse(
                    responseCode = "400",
                    description =
                            "Dados obrigatórios não informados"
            )

    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(


            @Valid
            @RequestBody
            LoginRequest request

    ) {



        AuthenticationResponse response =
                authService.login(request);



        return ResponseEntity.ok(response);

    }

}