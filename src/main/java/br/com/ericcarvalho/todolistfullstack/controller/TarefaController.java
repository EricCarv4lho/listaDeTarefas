package br.com.ericcarvalho.todolistfullstack.controller;

import br.com.ericcarvalho.todolistfullstack.entidades.Tarefa;
import br.com.ericcarvalho.todolistfullstack.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {


    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody @Valid Tarefa tarefa) {

        tarefaService.adicionarTarefa(tarefa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarefa>> listarTarefas() {

        return new ResponseEntity<>(tarefaService.listarTarefas(),HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Tarefa>> listarUmaTarefa(@PathVariable Long id) {
     return new ResponseEntity<>(tarefaService.listarUmaTarefa(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa( @PathVariable Long id, @RequestBody Tarefa tarefa) {
        return new ResponseEntity<>(tarefaService.atualizarTarefa(id, tarefa),HttpStatus.OK);
    }


}
