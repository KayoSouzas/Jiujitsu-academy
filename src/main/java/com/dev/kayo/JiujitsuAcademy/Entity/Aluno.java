package com.dev.kayo.JiujitsuAcademy.Entity;

import com.dev.kayo.JiujitsuAcademy.Enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.Enums.Matricula;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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


}
