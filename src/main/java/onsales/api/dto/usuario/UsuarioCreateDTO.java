package onsales.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UsuarioCreateDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String role,
        @NotNull
        LocalDateTime criacao
) {
}
