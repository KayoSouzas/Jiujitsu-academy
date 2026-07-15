package com.dev.kayo.JiujitsuAcademy.repository;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
