package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.mapper.AlunoMapper;
import com.dev.kayo.JiujitsuAcademy.repository.AlunoRepository;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponse> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        return AlunoMapper.toAlunoResponse(alunos);

    }
    /**

     Busca aluno no repositorio.*
     @return  Aluno com o id passado*/
    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElse(null);

    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void delete(Long id) {
        alunoRepository.deleteById(id);

    }

    public Optional<Aluno> update(Long alunoId, Aluno updateAluno) {
        Optional<Aluno> optAluno = alunoRepository.findById(alunoId);

        if (optAluno.isPresent()) {


            Aluno aluno = optAluno.get();

            aluno.setNome(updateAluno.getNome());
            aluno.setEmail(updateAluno.getEmail());
            aluno.setMatricula(updateAluno.getMatricula());
            aluno.setFaixaAtual(updateAluno.getFaixaAtual());
            aluno.setIdade(updateAluno.getIdade());

            alunoRepository.save(aluno);
            return Optional.of(aluno);
        }
        return Optional.empty();
    }


}
