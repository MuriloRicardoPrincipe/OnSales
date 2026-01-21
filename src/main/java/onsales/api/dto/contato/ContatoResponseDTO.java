package onsales.api.dto.contato;

import jakarta.validation.constraints.NotBlank;
import onsales.api.model.Contato;

import java.util.UUID;

public record ContatoResponseDTO (
        UUID id,
        UUID clienteId,
        String telefone,
        String email,
        String celular
){
    public ContatoResponseDTO(Contato contato){
        this(contato.getId(),
            contato.getCliente().getId(),
            contato.getTelefone(),
            contato.getEmail(),
            contato.getCelular());
    }
}
