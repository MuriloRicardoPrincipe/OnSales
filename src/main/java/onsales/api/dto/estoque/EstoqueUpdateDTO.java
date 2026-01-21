package onsales.api.dto.estoque;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import onsales.api.model.Produto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstoqueUpdateDTO(
        @NotNull(message = "Produto não localizado!")
        UUID produtoId,

        @NotNull
        @Positive
        Integer quantidade
) {
}
