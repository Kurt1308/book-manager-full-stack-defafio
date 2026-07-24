package com.bookmanager.backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "RegisterRequest",
        description = "Objeto utilizado para cadastro de um novo usuário"
)
public class RegisterRequest {

    @Schema(
            description = "Nome completo do usuário",
            example = "João da Silva",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(
            description = "Email do usuário",
            example = "joao@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Schema(
            description = "Senha do usuário (mínimo de 6 caracteres)",
            example = "123456",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password"
    )
    @NotBlank(message = "Password is required")
    @Size(
            min = 6,
            message = "Password must have at least 6 characters"
    )
    private String password;

}