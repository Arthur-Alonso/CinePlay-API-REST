CREATE TABLE filmes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracao INT NOT NULL,
    genero VARCHAR(50) NOT NULL,
    classificacao_etaria VARCHAR(10) NOT NULL,
    sinopse VARCHAR(2000) NOT NULL,
    ano_lancamento INT NOT NULL,
    status_exibicao VARCHAR(50) NOT NULL
);