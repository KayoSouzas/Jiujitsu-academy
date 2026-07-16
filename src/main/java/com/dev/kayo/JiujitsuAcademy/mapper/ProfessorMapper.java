package com.dev.kayo.JiujitsuAcademy.mapper;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.request.ProfessorRequest;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResponse;

import java.util.List;

public class ProfessorMapper {

    public static Professor toProfessor(ProfessorRequest request) {

        return Professor.builder()
                .nome(request.nome())
                .email(request.email())
                .faixaAtual(request.faixaAtual())
                .idade(request.idade())
                .build();

    }

    public static ProfessorResponse toProfessorResponse(Professor professor) {

        return ProfessorResponse.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .faixaAtual(professor.getFaixaAtual())
                .idade(professor.getIdade())
                .build();

    }

    public static List<ProfessorResponse> toProfessorResponse(List<Professor> professores) {

        return professores.stream()
                .map(ProfessorMapper::toProfessorResponse)
                .toList();

    }


}
