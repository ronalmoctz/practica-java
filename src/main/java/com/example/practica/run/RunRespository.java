package com.example.practica.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRespository {
        private List<Run> runs = new ArrayList<>();

        List<Run> FindAll() {
                return runs;
        }

        Run findById(Integer id) {
                return runs.stream().filter(run -> run.id() == id).findFirst().get();
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
