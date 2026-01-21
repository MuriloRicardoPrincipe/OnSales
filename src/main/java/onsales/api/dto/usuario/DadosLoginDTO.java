package onsales.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDTO (@NotBlank(message = "Informe o nome!") String email,
                             @NotBlank(message = "Informe a senha!") String senha) {
        }
