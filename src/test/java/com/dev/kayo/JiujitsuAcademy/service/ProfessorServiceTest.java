package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.exceptions.ProfessorNãoEncontradoException;
import com.dev.kayo.JiujitsuAcademy.repository.ProfessorRepository;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;


    @Test
    void findAll_deveRetornarLista_quandoListaExistir() {

        List<Professor> listProfessor = new ArrayList<>();

        Professor professor = new Professor();

        professor.setNome("Carlos Gracie");
        professor.setId(1L);
        professor.setIdade(10);

        Professor professor2 = new Professor();

        professor2.setId(2L);
        professor2.setNome("Mestre cobra");
        professor2.setIdade(15);

        listProfessor.add(professor);
        listProfessor.add(professor2);

        when(professorRepository.findAll()).thenReturn(listProfessor);


        List<ProfessorResponse> professores = professorService.findAll();


        assertNotNull(professores);
        assertFalse(professores.isEmpty());
        assertEquals(2, professores.size());
        assertEquals("Carlos Gracie", professores.get(0).nome());
        assertEquals("Mestre cobra", professores.get(1).nome());

        verify(professorRepository, times(1)).findAll();

    }

    @Test
    void findAll_deveRetornarVazio_quandoNaoExistirLista(){

        List<Professor> listProfessores = new ArrayList<>();


        when(professorRepository.findAll()).thenReturn(listProfessores);

        List<ProfessorResponse> professorList = professorService.findAll();

        assertNotNull(professorList);
        assertTrue(professorList.isEmpty());
        assertEquals(0, professorList.size());

        verify(professorRepository, times(1)).findAll();

    }



    @Test
    void findById_deveRetornarProfessor_quandoExistir() {
    Professor professor = new Professor();

    professor.setId(1L);
    professor.setNome("Helio Gracie");
    professor.setIdade(20);

    when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        ProfessorResponse  profById = professorService.findById(1L);


        assertEquals("Helio Gracie", profById.nome());
        assertNotNull(profById);

        verify(professorRepository, times(1)).findById(1L);

    }

    @Test
    void findById_DeveRetornarException_quandoNaoExistir(){

        when(professorRepository.findById(10l)).thenReturn(Optional.empty());

       assertThrows (ProfessorNãoEncontradoException.class, () -> professorService.findById(10l));

        verify(professorRepository, times(1)).findById(10L);

    }


    @Test
    void save() {

        Professor professor = new Professor();

        professor.setId(1L);
        professor.setNome("Carlos Gracie");
        professor.setIdade(20);
        professor.setEmail("carlosgracie22@gmail.com");
        professor.setFaixaAtual(Faixa.ROXA);

        when(professorRepository.save(professor)).thenReturn(professor);

        Professor professorSaved = professorService.save(professor);

        assertNotNull(professorSaved);
        assertEquals("Carlos Gracie", professorSaved.getNome());
        assertEquals("carlosgracie22@gmail.com",  professorSaved.getEmail());


        verify(professorRepository, times(1)).save(professor);

    }

    @Test
    void update() {

        Professor professor = new Professor();

        professor.setId(1L);
        professor.setNome("Carlos Gracie");
        professor.setIdade(20);
        professor.setEmail("carlosgracie55@gmail.com");
        professor.setFaixaAtual(Faixa.PRETA);

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        Optional<Professor> updateProfessor = professorService.update(1L, professor);

        assertNotNull(updateProfessor);
        assertEquals("Carlos Gracie", updateProfessor.get().getNome());
        assertEquals("carlosgracie55@gmail.com",   updateProfessor.get().getEmail());

        verify(professorRepository, times(1)).findById(1L);


    }

    @Test
    void delete() {

        professorService.delete(1L);

        verify(professorRepository, times(1)).deleteById(1L);

    }
}