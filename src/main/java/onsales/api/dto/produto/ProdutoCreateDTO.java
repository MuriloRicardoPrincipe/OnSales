package onsales.api.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoCreateDTO(
        @NotBlank(message = "Informe o nome!")
        String nome,
        @NotBlank(message = "Informe uma descrição!")
        String descricao,

        @NotNull
        @Positive
        BigDecimal preco
) {
}
