package com.dev.kayo.JiujitsuAcademy.entity;

import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;

    @NotNull
    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @NotNull
    @Column(name = "horario_fim", nullable = false)
    private LocalTime horarioFim;

    @NotNull
    @Positive
    @Column(name = "capacidade_maxima")
    private Integer capacidadeMaxima;


    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;


    @ManyToMany
    @JoinTable(
            name = "aluno_turma",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    @Builder.Default
    private Set<Aluno> alunos = new HashSet<>();

}
