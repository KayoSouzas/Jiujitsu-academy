package com.dev.kayo.JiujitsuAcademy.mapper;

import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResumoResponse;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResumoResponse;
import com.dev.kayo.JiujitsuAcademy.response.TurmaResponse;

import java.util.List;

public class TurmaMapper {
    public static Turma toTurma(TurmaRequest request) {

        return Turma.builder()
                .nome(request.nome())
                .horarioFim(request.horarioFim())
                .capacidadeMaxima(request.capacidadeMaxima())
                .horarioInicio(request.horarioInicio())
                .diaSemana(request.diaSemana())
                .build();

    }

    public static TurmaResponse toTurmaResponse(Turma turma) {
        return new TurmaResponse(
                turma.getNome(),
                turma.getId(),
                turma.getHorarioInicio(),
                turma.getHorarioFim(),
                turma.getCapacidadeMaxima(),
                turma.getDiaSemana(),
                new ProfessorResumoResponse(turma.getProfessor().getNome(), turma.getProfessor().getFaixaAtual()),
                turma.getAlunos().stream()
                        .map(aluno -> new AlunoResumoResponse(aluno.getNome(), aluno.getFaixaAtual()))
                        .toList()
        );
    }


    public static List<TurmaResponse> toResponseList(List<Turma> turmas) {

        return turmas.stream()
                .map(TurmaMapper::toTurmaResponse)
                .toList();
    }


}
