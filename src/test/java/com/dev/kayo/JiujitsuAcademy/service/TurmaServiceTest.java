package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.entity.Turma;
import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import com.dev.kayo.JiujitsuAcademy.exceptions.TurmaNaoEncontradaException;
import com.dev.kayo.JiujitsuAcademy.repository.AlunoRepository;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.repository.TurmaRepository;
import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
import com.dev.kayo.JiujitsuAcademy.response.TurmaResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurmaServiceTest {

    @Mock
    private TurmaRepository turmaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private TurmaService turmaService;

    @Test
    void save() {

        Professor professor = new Professor();
        professor.setId(1L);

        TurmaRequest turma = new TurmaRequest(1L, "Baby", DiaSemana.QUARTA, LocalTime.of(18, 0), LocalTime.of(19, 0), 10, 1L);


        Turma turma1 = new Turma();

        turma1.setId(1L);
        turma1.setNome("Baby");

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(turmaRepository.save(any(Turma.class))).thenReturn(turma1);

        Turma savedTurma = turmaService.save(turma);

        assertNotNull(savedTurma);
        assertEquals("Baby", savedTurma.getNome());

        verify(turmaRepository, times(1)).save(any(Turma.class));

    }

    @Test
    void findAll_DeveRetornarLista_quandoExistirElementos() {

        Professor professor = new Professor();
        professor.setId(1L);

        Turma turma1 = new Turma();

        turma1.setId(1L);
        turma1.setNome("Baby");
        turma1.setDiaSemana(DiaSemana.QUARTA);
        turma1.setHorarioInicio(LocalTime.of(18, 0));
        turma1.setHorarioFim(LocalTime.of(19, 0));
        turma1.setProfessor(professor);

        Turma turma2 = new Turma();

        turma2.setId(2L);
        turma2.setNome("Adulto");
        turma2.setDiaSemana(DiaSemana.TERCA);
        turma2.setHorarioInicio(LocalTime.of(20, 0));
        turma2.setHorarioFim(LocalTime.of(21, 0));
        turma2.setProfessor(professor);

        List<Turma> turmas = List.of(turma1, turma2);


        when(turmaRepository.findAll()).thenReturn(turmas);

        List<TurmaResponse> resultado = turmaService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Adulto", resultado.get(1).nome());
        assertEquals("Baby", resultado.get(0).nome());

        verify(turmaRepository, times(1)).findAll();

    }

    @Test
    void findAll_deveRetornarVazio_quandoNaoExistirElementos() {

        when(turmaRepository.findAll()).thenReturn(List.of());

        List<TurmaResponse> resultado = turmaService.findAll();

        assertNotNull(resultado);


        verify(turmaRepository, times(1)).findAll();

    }


    @Test
    void findById_deveRetornarTurma_quandoExistirTurma() {

        Turma turma = new Turma();

        turma.setId(1L);
        turma.setNome("Baby");
        turma.setDiaSemana(DiaSemana.QUARTA);
        turma.setHorarioInicio(LocalTime.of(18, 0));
        turma.setHorarioFim(LocalTime.of(19, 0));
        turma.setCapacidadeMaxima(10);

        when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));

        Turma byId = turmaService.findById(1L);

        assertNotNull(byId);
        assertEquals("Baby", byId.getNome());

        verify(turmaRepository, times(1)).findById(1L);


    }

    @Test
    void findById_deveRetornarVazio_quandoNaoExistirTurma(){

        when(turmaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TurmaNaoEncontradaException.class, () -> turmaService.findById(99L));

        verify(turmaRepository, times(1)).findById(99L);

    }

    @Test
    void update() {

        Turma turma = new Turma();

        turma.setId(1L);
        turma.setNome("Baby");
        turma.setDiaSemana(DiaSemana.QUARTA);
        turma.setHorarioInicio(LocalTime.of(18, 0));
        turma.setHorarioFim(LocalTime.of(19, 0));
        turma.setCapacidadeMaxima(10);

        Turma turmaUpdate = new Turma();

        turmaUpdate.setId(1L);
        turmaUpdate.setNome("Campeões");
        turmaUpdate.setDiaSemana(DiaSemana.SEGUNDA);
        turmaUpdate.setHorarioInicio(LocalTime.of(20, 0));
        turmaUpdate.setHorarioFim(LocalTime.of(21, 0));
        turmaUpdate.setCapacidadeMaxima(20);

        when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));

        Optional<Turma> byId = turmaService.update(1L, turmaUpdate);

        assertTrue(byId.isPresent());
        assertEquals("Campeões",  byId.get().getNome());

        verify(turmaRepository, times(1)).findById(1L);



    }

    @Test
    void delete() {

        turmaService.delete(1L);

        verify(turmaRepository, times(1)).deleteById(1L);

    }

    @Test
    void matricularAluno() {

        Turma turma = new Turma();

        turma.setId(1L);
        turma.setNome("Baby");
        turma.setAlunos(new HashSet<>());

        Aluno aluno = new Aluno();

        aluno.setId(1L);
        aluno.setNome("Kayo souza");
        aluno.setTurmas(new HashSet<>());

        when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(turmaRepository.save(turma)).thenReturn(turma);

        Turma turmaSaved = turmaService.matricularAluno(1L, 1L);

        assertNotNull(turmaSaved);
        assertEquals("Baby", turmaSaved.getNome());
        assertEquals(1L, turmaSaved.getId());

        verify(turmaRepository, times(1)).findById(1L);
        verify(alunoRepository, times(1)).findById(1L);
        verify(turmaRepository, times(1)).save(turma);

    }

    @Test
    void matricularAluno_DeveLancarExcecao_quandoTurmaNaoExistir() {

        when(turmaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> turmaService.matricularAluno(99L, 1L));

        verify(turmaRepository, times(1)).findById(99L);
        verify(alunoRepository, never()).findById(any());
        verify(turmaRepository, never()).save(any(Turma.class));
    }

}