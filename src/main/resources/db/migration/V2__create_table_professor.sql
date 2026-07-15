CREATE TABLE professor(
    id serial PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    idade INTEGER NOT NULL,
    faixa_atual VARCHAR(50) NOT NULL
);