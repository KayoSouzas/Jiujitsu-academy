CREATE TABLE aluno(
    id serial PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    faixa_atual VARCHAR(50) NOT NULL,
    matricula VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    idade INTEGER NOT NULL,
    data_matricula DATE NOT NULL
);