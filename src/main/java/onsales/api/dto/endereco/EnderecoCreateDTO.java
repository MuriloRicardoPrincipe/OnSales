package onsales.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import onsales.api.model.Endereco;

import java.util.UUID;

public record EnderecoCreateDTO(
        @NotNull(message = "Informe o Cliente é obrigatório!")
        UUID clienteId,
        @NotBlank(message = "Informe o rua é obrigatório!")
        String rua,
        @NotBlank(message = "Informe o numero é obrigatório!")
        String numero,
        @NotBlank(message = "Informe o complemento é obrigatório!")
        String complemento,
        @NotBlank(message = "Informe o bairro é obrigatório!")
        String bairro,
        @NotBlank(message = "Informe o cidade é obrigatório!")
        String cidade,
        @NotBlank(message = "Informe o estado é obrigatório!")
        String estado,
        @NotBlank(message = "Informe o cep é obrigatório!")
        String cep
) {
}
