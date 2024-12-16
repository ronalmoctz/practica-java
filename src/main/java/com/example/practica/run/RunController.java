package com.example.practica.run;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/1")
    Run findById() {
        return runRespository.findById(1);
    }

}
