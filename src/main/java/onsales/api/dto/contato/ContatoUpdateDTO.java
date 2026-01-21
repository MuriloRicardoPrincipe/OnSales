package onsales.api.dto.contato;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ContatoUpdateDTO (

        @NotBlank(message = "Telefone não informado!")
        String telefone,

        @NotBlank(message = "E-mail não informado!")
        String email,

        @NotBlank(message = "Celular não informado!")
        String celular
){
}
