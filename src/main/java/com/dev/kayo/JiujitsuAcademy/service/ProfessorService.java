package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> findAll() {
        return professorRepository.findAll();

    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);

    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);

    }


    public Optional<Professor> update(Long id, Professor professor) {

        Optional<Professor> optProfessor = professorRepository.findById(id);

        if (optProfessor.isPresent()) {

            Professor newProfessor = optProfessor.get();

            newProfessor.setNome(professor.getNome());
            newProfessor.setEmail(professor.getEmail());
            newProfessor.setIdade(professor.getIdade());
            newProfessor.setFaixaAtual(professor.getFaixaAtual());


            professorRepository.save(newProfessor);
            return Optional.of(newProfessor);
        }
        return Optional.empty();

    }

    public void delete(Long id) {
        professorRepository.deleteById(id);

    }

}
