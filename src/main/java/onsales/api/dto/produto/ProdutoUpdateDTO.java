package onsales.api.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoUpdateDTO(
        @NotBlank(message = "Informe o nome!")
        String nome,
        @NotBlank(message = "Informe uma descrição!")
        String descricao,
        @NotNull
        @Positive
        BigDecimal preco
) {
}
