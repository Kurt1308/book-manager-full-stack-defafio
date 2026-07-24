package com.bookmanager.backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "LoginRequest",
        description = "Objeto utilizado para autenticação de usuários"
)
public class LoginRequest {

    @Schema(
            description = "Email do usuário",
            example = "joao@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Schema(
            description = "Senha do usuário",
            example = "123456",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password"
    )
    @NotBlank(message = "Password is required")
    private String password;

}