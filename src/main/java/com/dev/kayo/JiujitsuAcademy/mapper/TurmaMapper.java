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
                .professor(request.professor())
                .diaSemana(request.diaSemana())
                .build();

    }

    public static TurmaResponse toTurmaResponse(Turma turma) {

        return TurmaResponse.builder()
                .nome(turma.getNome())
                .horarioFim(turma.getHorarioFim())
                .capacidadeMaxima(turma.getCapacidadeMaxima())
                .horrarioInicio(turma.getHorarioInicio())
                .professor(turma.getProfessor())
                .diaSemana(turma.getDiaSemana())


                .build();

    }

    public static List<TurmaResponse> toResponseList(List<Turma> turmas) {

        return turmas.stream()
                .map(TurmaMapper::toTurmaResponse)
                .toList();
    }


}
