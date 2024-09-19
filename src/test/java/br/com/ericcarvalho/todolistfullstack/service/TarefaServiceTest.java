package br.com.ericcarvalho.todolistfullstack.service;

import br.com.ericcarvalho.todolistfullstack.entidades.Tarefa;
import br.com.ericcarvalho.todolistfullstack.entidades.enums.Prioridade;
import br.com.ericcarvalho.todolistfullstack.entidades.enums.Status;
import br.com.ericcarvalho.todolistfullstack.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        @DisplayName("Deve retornar uma lista de tarefas")
        public void deveRetornarUmaListaDeTarefas(){



        }





}
