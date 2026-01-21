package onsales.api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuarioDTO(
        @NotBlank(message = "Informe o nome!")
        String nome,

        @NotBlank(message = "Informe o email!")
        @Email
        String email,

        @NotBlank(message = "Informe a senha!")
        String senha
) {
}
