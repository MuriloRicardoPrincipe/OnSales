package onsales.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_token")
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    private Usuario usuario;

    private Instant expiracao;

    public RefreshToken(String token, Usuario usuario, Instant expiracao) {
        this.token = token;
        this.usuario = usuario;
        this.expiracao = expiracao;
    }

    public boolean isExpirado() {
        return Instant.now().isAfter(this.expiracao);
    }
}

