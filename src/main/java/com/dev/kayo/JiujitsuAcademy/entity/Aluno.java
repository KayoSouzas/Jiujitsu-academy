package com.dev.kayo.JiujitsuAcademy.entity;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.enums.Matricula;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull
    private String nome;

    @Column(name = "idade", nullable = false)
    @NotNull
    private int idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "faixa_atual", nullable = false)
    @NotNull
    private Faixa faixaAtual;

    @Column(name = "email", nullable = false, unique = true)
    @NotNull
    @Email
    private String email;

    @NotNull
    @PastOrPresent
    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "matricula", nullable = false)
    private Matricula matricula;

    @ManyToMany(mappedBy = "alunos")
    @Builder.Default
    private Set<Turma> turmas = new HashSet<>();

}
