package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.exceptions.ProfessorNãoEncontradoException;
import com.dev.kayo.JiujitsuAcademy.mapper.ProfessorMapper;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<ProfessorResponse> findAll() {
        List<Professor> professores = professorRepository.findAll();
        return ProfessorMapper.toProfessorResponse(professores);

    }

    public ProfessorResponse findById(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ProfessorNãoEncontradoException("Professor não encontrado"));

        return ProfessorMapper.toProfessorResponse(professor);

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
