package com.example.practica.run;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class RunRespository {
        private static final Logger log = LoggerFactory.getLogger(RunRespository.class);
        private final JdbcClient jdbcClient;

        public RunRespository(JdbcClient jdbcClient) {
                this.jdbcClient = jdbcClient;
        }

        public List<Run> findAll() {
                return jdbcClient.sql("select * from Run")
                                .query(Run.class)
                                .list();
        }
}
