CREATE TABLE refresh_token (
    id BINARY(16) NOT NULL,
    token VARCHAR(255) NOT NULL,
    usuario_id BINARY(16) NOT NULL,
    expiracao TIMESTAMP NOT NULL,

    CONSTRAINT pk_refresh_token PRIMARY KEY (id),
    CONSTRAINT uk_refresh_token_token UNIQUE (token),
    CONSTRAINT fk_refresh_token_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
        ON DELETE CASCADE
);