package com.dev.kayo.JiujitsuAcademy.repository;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    boolean existsByProfessorAndDiaSemanaAndHorarioInicio(Professor professor, DiaSemana diaSemana, LocalTime horarioInicio);

}
