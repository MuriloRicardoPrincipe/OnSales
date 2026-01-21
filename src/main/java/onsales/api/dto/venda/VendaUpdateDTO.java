package onsales.api.dto.venda;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record VendaUpdateDTO (

        @NotNull(message = "Informe o cliente!")
        UUID clienteId
){
}
