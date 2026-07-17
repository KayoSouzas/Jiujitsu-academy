package com.dev.kayo.JiujitsuAcademy.mapper;

import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
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

        return TurmaResponse.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .horarioFim(turma.getHorarioFim())
                .capacidadeMaxima(turma.getCapacidadeMaxima())
                .horarioInicio(turma.getHorarioInicio())
                .professor(turma.getProfessor())
                .diaSemana(turma.getDiaSemana())
                .alunos(turma.getAlunos().stream()
                        .map(AlunoMapper::toAlunoResponse)
                        .toList())
                .build();

    }

    public static List<TurmaResponse> toResponseList(List<Turma> turmas) {

        return turmas.stream()
                .map(TurmaMapper::toTurmaResponse)
                .toList();
    }


}
