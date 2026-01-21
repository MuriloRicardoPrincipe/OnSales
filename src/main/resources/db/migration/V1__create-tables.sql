
CREATE TABLE usuario (
    id BINARY(16) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cliente (
    id BINARY(16) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    tipo_documento VARCHAR(10) NOT NULL,
    documento VARCHAR(14) NOT NULL UNIQUE,
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE endereco (
    id BINARY(16) NOT NULL,
    cliente_id BINARY(16) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep VARCHAR(20),
    PRIMARY KEY (id),
    CONSTRAINT fk_endereco_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE contato (
    id BINARY(16) NOT NULL,
    cliente_id BINARY(16) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(150),
    celular VARCHAR(20),
    PRIMARY KEY (id),
    CONSTRAINT fk_contato_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE produto (
    id BINARY(16) NOT NULL,
    nome VARCHAR(250) NOT NULL,
    descricao TEXT,
    preco DECIMAL(15,2) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE estoque (
    id BINARY(16) NOT NULL,
    produto_id BINARY(16) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_estoque_produto
        FOREIGN KEY (produto_id) REFERENCES produto(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE venda (
    id BINARY(16) NOT NULL,
    cliente_id BINARY(16) NOT NULL,
    total DECIMAL(15,2) NOT NULL,
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
        ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE venda_item (
    id BINARY(16) NOT NULL,
    venda_id BINARY(16) NOT NULL,
    produto_id BINARY(16) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    total DECIMAL(15,2) NOT NULL,
    criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_venda
        FOREIGN KEY (venda_id)
        REFERENCES venda(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_venda_produto
        FOREIGN KEY (produto_id)
        REFERENCES produto(id)
        ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE INDEX idx_venda_cliente ON venda(cliente_id);
CREATE INDEX idx_venda_item_venda ON venda_item(venda_id);
CREATE INDEX idx_venda_item_produto ON venda_item(produto_id);

