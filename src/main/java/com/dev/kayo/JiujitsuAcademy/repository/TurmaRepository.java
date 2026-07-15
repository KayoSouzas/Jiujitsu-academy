package com.dev.kayo.JiujitsuAcademy.repository;

import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
