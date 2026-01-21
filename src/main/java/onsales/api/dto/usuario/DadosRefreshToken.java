package onsales.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosRefreshToken(@NotBlank String refreshToken) {
}
