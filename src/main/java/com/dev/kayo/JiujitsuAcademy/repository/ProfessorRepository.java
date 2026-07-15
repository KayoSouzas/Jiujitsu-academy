package com.dev.kayo.JiujitsuAcademy.repository;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
