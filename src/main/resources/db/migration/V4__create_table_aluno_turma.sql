CREATE TABLE aluno_turma (
    aluno_id BIGINT NOT NULL,
    turma_id BIGINT NOT NULL,
    PRIMARY KEY (aluno_id, turma_id),
    CONSTRAINT fk_aluno_turma_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    CONSTRAINT fk_aluno_turma_turma FOREIGN KEY (turma_id) REFERENCES turma(id)
);