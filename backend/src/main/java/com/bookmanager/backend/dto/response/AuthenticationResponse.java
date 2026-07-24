package com.bookmanager.backend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AuthenticationResponse",
        description = "Resposta retornada após autenticação ou cadastro do usuário"
)
public class AuthenticationResponse {

    @Schema(
            description = "Token JWT utilizado para autenticação nas rotas protegidas",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE5MjAwMDAwLCJleHAiOjE3MTkyODY0MDB9.qwerty123456789abcdefghijklmnopqrstuvwxyz"
    )
    private String token;

}