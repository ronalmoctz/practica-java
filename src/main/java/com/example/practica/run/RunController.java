package com.example.practica.run;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRespository runRespository;

    public RunController(RunRespository runRespository) {
        this.runRespository = runRespository;
    }

    @GetMapping("")
    List<Run> FindAll() {
        return runRespository.FindAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRespository.findById(id);
        if (run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    // post (como es tipo req.body debemos tener en cuenta el uso de @RequestBody)
    // ya que esto sera un nuevo run pero en formato JSON
    @ResponseStatus(HttpStatus.CREATED) // -> me retorna un 201 para conocer que fue creado
    @PostMapping("")
    void create(@Validated @RequestBody Run run) {
        runRespository.create(run);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT) // -> me retorna un 204 para conocer que fue actualizado
    @PutMapping("/{id}")
    void update(@Validated @RequestBody Run run, @PathVariable Integer id) {
        runRespository.update(run, id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT) // -> me retorna un 204 para conocer que fue eliminado
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRespository.delete(id);
    }

}
