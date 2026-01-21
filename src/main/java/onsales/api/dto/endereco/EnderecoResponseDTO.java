package onsales.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import onsales.api.model.Endereco;

import java.util.UUID;

public record EnderecoResponseDTO (
        UUID id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        UUID clienteId
) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(   endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getCliente().getId()
        );
    }
}
