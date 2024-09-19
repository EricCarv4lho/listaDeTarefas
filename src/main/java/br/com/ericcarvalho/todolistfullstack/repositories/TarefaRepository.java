package br.com.ericcarvalho.todolistfullstack.repositories;

import br.com.ericcarvalho.todolistfullstack.entidades.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
