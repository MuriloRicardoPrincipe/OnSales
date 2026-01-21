package onsales.api.dto.estoque;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record EstoqueCreateDTO(
        @NotNull(message = "Produto não localizado!")
        UUID produtoId,
        @NotNull
        @Positive
        Integer quantidade

) {
}
