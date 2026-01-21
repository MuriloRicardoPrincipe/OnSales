package onsales.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoUpdateDTO(
        @NotBlank(message = "Rua não informado!")
        String rua,
        @NotBlank(message = "Numero residencial não informado!")
        String numero,
        @NotBlank(message = "complemento não informado!")
        String complemento,
        @NotBlank(message = "Bairro não informado!")
        String bairro,
        @NotBlank(message = "Cidade não informado!")
        String cidade,
        @NotBlank(message = "Estado não informado!")
        String estado,
        @NotBlank(message = "CEP não informado!")
        String cep
) {
}
