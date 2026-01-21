package onsales.api.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import onsales.api.model.TipoDocumento;

import java.time.LocalDateTime;

public record ClienteCreateDTO(
        @NotBlank(message = "Nome é obrigatório!")
        String nome,
        @NotNull(message = "Tipo de Documento é obrigatório, envie CNPJ ou CPF!")
        TipoDocumento tipoDocumento,
        @NotBlank
        @Pattern(regexp = "\\d{11}|\\d{14}",
                message = "Documento deve conter 11 (CPF) ou 14 (CNPJ) dígitos")
        String documento,

        LocalDateTime criacao ) {

}
