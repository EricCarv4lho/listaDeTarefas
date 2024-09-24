package br.com.ericcarvalho.todolistfullstack.service;

import br.com.ericcarvalho.todolistfullstack.entidades.Tarefa;
import br.com.ericcarvalho.todolistfullstack.entidades.enums.Prioridade;
import br.com.ericcarvalho.todolistfullstack.entidades.enums.Status;
import br.com.ericcarvalho.todolistfullstack.exceptions.TarefaNotFoundException;
import br.com.ericcarvalho.todolistfullstack.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TarefaServiceTest {
    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar tarefa com sucesso.")
    public void deveCriarTarefaComSucesso(){

            Tarefa tarefa = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);

            when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

            Tarefa resultado = tarefaService.adicionarTarefa(tarefa);

            assertNotNull(resultado);
            assertEquals("todo 1", resultado.getTitulo());

            verify(tarefaRepository, times(1)).save(tarefa);
        }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar tarefa nula.")
    public void deveLancarExcecaoParaTarefaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            tarefaService.adicionarTarefa(null);
        });
    }


    //simula um cenário em que o repositório (no caso, tarefaRepository) falha ao tentar salvar uma tarefa, mesmo que a tarefa em si seja válida.
    @Test
    @DisplayName("Deve lançar exceção ao falhar salvar tarefa no repositório.")
    public void deveLancarExcecaoAoFalharSalvar() {
        Tarefa tarefa = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);

        when(tarefaRepository.save(any(Tarefa.class))).thenThrow(new RuntimeException("Erro ao salvar no banco"));

        assertThrows(RuntimeException.class, () -> {
            tarefaService.adicionarTarefa(tarefa);
        });
    }


    @Test
    @DisplayName("Deve retornar uma lista vazia se não houver tarefas")
    public void deveRetornarListaVaziaSeNaoHouverTarefas() {
        when(tarefaRepository.findAll()).thenReturn(List.of());

        List<Tarefa> resultado = tarefaService.listarTarefas();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma tarefa existente")
    public void deveRetornarUmaTarefaExistente() {
        Tarefa tarefa = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);
        Long id = 1L;

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.existsById(id)).thenReturn(true); // Adicionado mock aqui

        var resultado = tarefaService.listarUmaTarefa(id).orElse(null);

        assertNotNull(resultado);
        assertEquals("todo 1", resultado.getTitulo());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar listar uma tarefa que não existe")
    public void deveLancarExcecaoParaTarefaNaoExistente() {
        Long id = 1L;

        when(tarefaRepository.existsById(id)).thenReturn(false);

        assertThrows(TarefaNotFoundException.class, () -> {
            tarefaService.listarUmaTarefa(id);
        });
    }

    @Test
    @DisplayName("Deve deletar uma tarefa existente")
    public void deveDeletarUmaTarefaExistente() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));

        tarefaService.deletarTarefa(id);

        verify(tarefaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar uma tarefa que não existe")
    public void deveLancarExcecaoParaDeletarTarefaNaoExistente() {
        Long id = 1L;

        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TarefaNotFoundException.class, () -> {
            tarefaService.deletarTarefa(id);
        });
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente")
    public void deveAtualizarUmaTarefaExistente() {
        Long id = 1L;
        Tarefa tarefaExistente = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);
        Tarefa tarefaAtualizada = new Tarefa("todo 1 atualizado", "desc todo 1 atualizado", Prioridade.MEDIA, Status.CONCLUIDO);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefaExistente));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaAtualizada);

        Tarefa resultado = tarefaService.atualizarTarefa(id, tarefaAtualizada);

        assertNotNull(resultado);
        assertEquals("todo 1 atualizado", resultado.getTitulo());
        verify(tarefaRepository, times(1)).save(tarefaExistente);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar uma tarefa que não existe")
    public void deveLancarExcecaoParaAtualizarTarefaNaoExistente() {
        Long id = 1L;
        Tarefa tarefaAtualizada = new Tarefa("todo 1", "desc todo 1", Prioridade.ALTA, Status.PENDENTE);

        when(tarefaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TarefaNotFoundException.class, () -> {
            tarefaService.atualizarTarefa(id, tarefaAtualizada);
        });
    }












}
