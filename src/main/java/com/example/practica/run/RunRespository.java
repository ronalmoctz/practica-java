package com.example.practica.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRespository {
        private List<Run> runs = new ArrayList<>();

        List<Run> FindAll() {
                return runs;
        }

        Optional<Run> findById(Integer id) {
                return runs.stream().filter(run -> run.id() == id).findFirst();
        }

        // Se crea nuevo arguemento para un nuevo corredor
        // el cual es inmutable
        void create(Run run) {
                runs.add(run);
        }

        // reliza una busqueda, primero si existe la run si no existe
        // finaza, si existe continua y actualiza la run
        void update(Run run, Integer id) {
                Optional<Run> existingRun = findById(id);
                if (existingRun.isPresent()) {
                        runs.set(runs.indexOf(existingRun.get()), run);
                }
        }

        void delete(Integer id) {
                runs.removeIf(run -> run.id() == id);
        }

        @PostConstruct
        private void init() {
                runs.add(new Run(1, "Monday Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5,
                                Location.INDOOR));
                runs.add(new Run(2, "Sunday Morning Run", LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                                LocalDateTime.now().plus(2,
                                                ChronoUnit.HOURS),
                                13, Location.OUTDOOR));
                runs.add(new Run(3, "Saturday Evening Run", LocalDateTime.now().plus(2, ChronoUnit.MINUTES),
                                LocalDateTime.now().plus(3,
                                                ChronoUnit.HOURS),
                                8, Location.OUTDOOR));
        }
}
