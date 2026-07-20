package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.repository.AlunoRepository;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dev.kayo.JiujitsuAcademy.enums.Matricula.ATIVA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;


    @InjectMocks
    private AlunoService alunoService;


    @Test
    void findById_deveRetornarAluno_quandoIdExiste() {

        Aluno aluno = new Aluno();

        aluno.setId(1L);
        aluno.setNome("Kayo Souza");


//         Testa a função buscando pelo id
//         @return se existir, retorna um optional de aluno
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));


//          teste real usando o service pra buscar o aluno
        Aluno resultado = alunoService.findById(1L);

//        confere se o resultado é o esperado
        assertNotNull(resultado);

//        compara para ver se o resultado é o esperado.
        assertEquals("Kayo Souza", resultado.getNome());

//        Verifica se o alunoRepository ta sendo chamado 1 vez
//        Verifica o COMPORTAMENTO do codigo.
        verify(alunoRepository, times(1)).findById(1L);

    }

    @Test
    void findById_deveRetornarNull_quandoAlunoNaoExiste() {

//        chama o repositoryMOCK e confirma se o retorno dele é empty
        when(alunoRepository.findById(2L)).thenReturn(Optional.empty());

//        Faz a busca REAL no service
        Aluno resultado = alunoService.findById(2L);

//        confirma se é o resultado esperado
        assertNull(resultado);

//        verifica o COMPORTAMENTO
//        (o repository foi chamado 1 vez?)
        verify(alunoRepository, times(1)).findById(2L);

    }

    @Test
    void delete() {

//         executa o metodo real do service
        alunoService.delete(1L);

//         verifica o comportamento e confere se o repository foi chamado uma unica vez
        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    void save_deveRetornarCREATED_quandoBodyEstiverCerto() {

        Aluno aluno = new Aluno();

        aluno.setNome("Kayo Souza");
        aluno.setId(1L);
        aluno.setIdade(10);
        aluno.setEmail("kayousouza@gmail.com");
        aluno.setFaixaAtual(Faixa.AZUL);

        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno resultado = alunoService.save(aluno);

        assertNotNull(resultado);

        assertEquals("Kayo Souza", resultado.getNome());

        verify(alunoRepository, times(1)).save(aluno);

    }


    @Test
    void update_deveRetornarAluno_quandoIdExiste() {
//        representa minha entidade no banco existente
        Aluno alunoExistente = new Aluno();

        alunoExistente.setId(1L);
        alunoExistente.setNome("Kayo Souza");
        alunoExistente.setFaixaAtual(Faixa.ROXA);
        alunoExistente.setIdade(19);
        alunoExistente.setEmail("kayousouza@gmail.com");

        Aluno alunoUpdate = new Aluno();

        alunoUpdate.setNome("Kayo Souza");
        alunoUpdate.setFaixaAtual(Faixa.MARROM);
        alunoUpdate.setIdade(22);
        alunoUpdate.setEmail("Kayosilva@gmail.com");

//      busca pelo repository mock
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoExistente));

//        aqui é o teste real
        Optional<Aluno> alunoNew = alunoService.update(1L, alunoUpdate);

//        esse metodo para verificar o conteudo sempre tem que vir antes do equals
//        por que senão quando chegar o (.GET) pode estourar valor null
        assertTrue(alunoNew.isPresent());

        assertEquals("Kayo Souza", alunoNew.get().getNome());

        verify(alunoRepository, times(1)).findById(1L);

    }

    @Test
    void update_deveRetornarVazio_quandoAlunoNaoExiste() {


//      guarda esse objeto no repository mock
        Aluno aluno = new Aluno();

        aluno.setNome("Kayo Souza");
        aluno.setId(1L);
        aluno.setIdade(12);

//       simula por uma procura no repositorio mock
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());


        Optional<Aluno> alunoNew = alunoService.update(99L, aluno);

//       aqui ele faz a verificação que meu objeto alunonew esteja vazio (é oque eu quero)
        assertFalse(alunoNew.isPresent());

//        verifica o comportamento ele espera que o repository seja chamado uma vez
        verify(alunoRepository, times(1)).findById(99L);

    }

    @Test
    void findAll_deveRetornarLista_quandoExisteAlunos() {

        List<Aluno> alunos = new ArrayList<>();

        Aluno aluno = new Aluno();

        aluno.setNome("Kayo Souza");
        aluno.setId(1L);
        aluno.setIdade(10);
        aluno.setMatricula(ATIVA);
        aluno.setFaixaAtual(Faixa.AZUL);
        aluno.setEmail("kayosouza1@gmail.com");

        Aluno aluno2 = new Aluno();

        aluno2.setNome("Julio Souza");
        aluno2.setId(2L);
        aluno2.setIdade(19);
        aluno2.setMatricula(ATIVA);
        aluno2.setFaixaAtual(Faixa.MARROM);
        aluno2.setEmail("JulioSouza@gmail.com");

        alunos.add(aluno);
        alunos.add(aluno2);


        when(alunoRepository.findAll()).thenReturn(alunos);

        List<AlunoResponse> resultado = alunoService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Kayo Souza", resultado.get(0).nome());
        assertEquals("Julio Souza", resultado.get(1).nome());

        verify(alunoRepository, times(1)).findAll();

    }

    @Test
    void findAll_DeveRetornarVazio_quandoNaoExiste() {
        List<Aluno> alunos = new ArrayList<>();


        when(alunoRepository.findAll()).thenReturn(alunos);

        List<AlunoResponse> alunoList = alunoService.findAll();

        assertNotNull(alunoList);
        assertTrue(alunoList.isEmpty());


        verify(alunoRepository, times(1)).findAll();


    }


}