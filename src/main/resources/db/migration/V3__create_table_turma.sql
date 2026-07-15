CREATE TABLE turma(

    id serial PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dia_semana VARCHAR(50) NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    capacidade_maxima INTEGER NOT NULL,
    professor_id BIGINT NOT NULL,
    CONSTRAINT fk_turma_professor FOREIGN KEY (professor_id) REFERENCES professor(id)
);