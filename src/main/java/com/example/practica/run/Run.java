package com.example.practica.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime CompleteOn,
        Integer miles,
        Location location) {
}
