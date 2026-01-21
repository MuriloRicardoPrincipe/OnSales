package onsales.api.dto.vendaItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record VendaItemUpdateDTO(
        @NotNull(message = "Informe o produto!")
        UUID produtoId,

        @NotNull
        @Positive
        Integer quantidade
) {
}
