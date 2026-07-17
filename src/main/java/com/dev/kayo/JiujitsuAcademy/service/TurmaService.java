package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.mapper.TurmaMapper;
import com.dev.kayo.JiujitsuAcademy.repository.AlunoRepository;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.repository.TurmaRepository;
import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public Turma save(TurmaRequest request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        boolean jaExiste = turmaRepository.existsByProfessorAndDiaSemanaAndHorarioInicio(professor, request.diaSemana(), request.horarioInicio());

        if (jaExiste) {
            throw new DuplicateRequestException("Você não pode cadastrar duas turmas iguais.");
        }

        Turma turma = TurmaMapper.toTurma(request);
        turma.setProfessor(professor);
        return turmaRepository.save(turma);

    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }


    public Turma findById(Long id) {
        return turmaRepository.findById(id).orElse(null);

    }

    public Optional<Turma> update(Long id, Turma updateTurma) {

        Optional<Turma> optTurma = turmaRepository.findById(id);

        if (optTurma.isPresent()) {

            Turma turma = optTurma.get();

            turma.setId(updateTurma.getId());
            turma.setNome(updateTurma.getNome());
            turma.setDiaSemana(updateTurma.getDiaSemana());
            turma.setHorarioInicio(updateTurma.getHorarioInicio());
            turma.setHorarioFim(updateTurma.getHorarioFim());
            turma.setCapacidadeMaxima(updateTurma.getCapacidadeMaxima());
            turma.setProfessor(updateTurma.getProfessor());

            turmaRepository.save(turma);
            return Optional.of(turma);

        }
        return Optional.empty();


    }


    public void delete(Long id) {
        turmaRepository.deleteById(id);

    }

    public Turma matricularAluno(Long turmaID, Long alunoID){

        Turma turma = turmaRepository.findById(turmaID).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));

        Aluno aluno = alunoRepository.findById(alunoID).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        if(turma.getAlunos().contains(aluno)){
            throw new IllegalStateException();

        }
        if (aluno.getTurmas().contains(turma)){

            throw new IllegalStateException();
        }

        turma.getAlunos().add(aluno);
        return turmaRepository.save(turma);
    }


}
