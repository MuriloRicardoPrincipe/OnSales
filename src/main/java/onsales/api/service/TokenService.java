package onsales.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import onsales.api.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "OnSales API";

    // ==========================
    // ACCESS TOKEN (JWT)
    // ==========================
    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getId().toString()) // UUID → String
                    .withClaim("role", usuario.getRole())
                    .withExpiresAt(gerarExpiracao(15)) // 15 min
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar access token", e);
        }
    }

    public UUID verificarAccessToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return UUID.fromString(decodedJWT.getSubject());

        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    // ==========================
    // REFRESH TOKEN
    // ==========================
    public String gerarRefreshToken(Usuario usuario) {
        // refresh token NÃO é JWT de acesso
        // é apenas um valor aleatório seguro
        return UUID.randomUUID().toString() + UUID.randomUUID();
    }

    private Instant gerarExpiracao(int minutos) {
        return LocalDateTime.now()
                .plusMinutes(minutos)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
