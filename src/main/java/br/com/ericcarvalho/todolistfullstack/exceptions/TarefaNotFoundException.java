package br.com.ericcarvalho.todolistfullstack.exceptions;

public class TarefaNotFoundException extends RuntimeException{
    public TarefaNotFoundException(Long id) {
        super("Tarefa não encontrada com ID: " + id);
    }
}
