package onsales.api.dto.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContatoCreateDTO(

        @NotNull(message = "Informe o Cliente é obrigatório!")
        UUID clienteId,

        @NotBlank(message = "Informe o telefone é obrigatório!")
        String telefone,

        @NotBlank(message = "Informe o email é obrigatório!")
        String email,

        @NotBlank(message = "Informe o celular é obrigatório!")
        String celular

) {}
