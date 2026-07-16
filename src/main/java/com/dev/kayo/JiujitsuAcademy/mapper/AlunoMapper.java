package com.dev.kayo.JiujitsuAcademy.mapper;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.enums.Matricula;
import com.dev.kayo.JiujitsuAcademy.request.AlunoRequest;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResponse;

import java.time.LocalDate;
import java.util.List;

public class AlunoMapper {

    public static Aluno toAluno(AlunoRequest request) {

        return Aluno.builder()
                .nome(request.nome())
                .idade(request.idade())
                .email(request.email())
                .faixaAtual(request.faixaAtual())
                .dataMatricula(LocalDate.now())
                .matricula(Matricula.ATIVA)
                .build();

    }

    public static AlunoResponse toAlunoResponse(Aluno aluno) {

        return AlunoResponse.builder()
                .nome(aluno.getNome())
                .idade(aluno.getIdade())
                .email(aluno.getEmail())
                .faixaAtual(aluno.getFaixaAtual())
                .matricula(aluno.getMatricula())
                .build();

    }

    public static List<AlunoResponse> toAlunoResponse(List<Aluno> alunos) {

        return alunos.stream()
                .map(AlunoMapper::toAlunoResponse)
                .toList();

    }


}
