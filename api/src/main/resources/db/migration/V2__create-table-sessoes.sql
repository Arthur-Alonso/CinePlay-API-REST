CREATE TABLE sessoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    horario_inicio TIME NOT NULL,
    sala VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    tipo_exibicao VARCHAR(20) NOT NULL,
    vagas_disponiveis INT NOT NULL,
    filme_id BIGINT NOT NULL,
    CONSTRAINT fk_filme FOREIGN KEY (filme_id) REFERENCES filmes(id)
);