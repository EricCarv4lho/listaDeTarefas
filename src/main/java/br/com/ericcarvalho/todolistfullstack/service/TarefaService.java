package br.com.ericcarvalho.todolistfullstack.service;

import br.com.ericcarvalho.todolistfullstack.controller.TarefaController;
import br.com.ericcarvalho.todolistfullstack.entidades.Tarefa;
import br.com.ericcarvalho.todolistfullstack.exceptions.TarefaNotFoundException;
import br.com.ericcarvalho.todolistfullstack.repositories.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {


    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTarefas(){
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> listarUmaTarefa(Long id){
        if(tarefaRepository.existsById(id)){
            return tarefaRepository.findById(id);
        }
        else {
            throw new TarefaNotFoundException(id);
        }
    }



    public Tarefa adicionarTarefa( Tarefa tarefa){

        if (tarefa == null) {
            throw new IllegalArgumentException("A tarefa não pode ser nula");
        }
        tarefa.setData(LocalDateTime.now());
        return  tarefaRepository.save(tarefa);

    }

    public void deletarTarefa(Long id){
        Optional<Tarefa> tarefaDeletar = tarefaRepository.findById(id);
        if(tarefaDeletar.isPresent()){
            tarefaRepository.deleteById(id);

        }
        else {
            throw new TarefaNotFoundException(id);
        }


    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada){
       Optional<Tarefa> tarefaExistenteOptional = tarefaRepository.findById(id);
        if(tarefaExistenteOptional.isPresent()){
            Tarefa tarefaExistente = tarefaExistenteOptional.get();
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefaExistente.setStatus(tarefaAtualizada.getStatus());

            return tarefaRepository.save(tarefaExistente);

        }
        else {
            throw new TarefaNotFoundException(id);
        }

    }
}
