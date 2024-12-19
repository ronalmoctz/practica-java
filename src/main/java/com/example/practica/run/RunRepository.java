package com.example.practica.run;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class RunRepository {
        private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
        private final JdbcClient jdbcClient;

        public RunRepository(JdbcClient jdbcClient) {
                this.jdbcClient = jdbcClient;
        }

        public List<Run> findAll() {
                return jdbcClient.sql("SELECT * FROM Run")
                                .query(Run.class)
                                .list();
        }

        public Optional<Run> findById(Integer id) {
                return jdbcClient.sql(
                                "SELECT id, title, started_on, completed_on, miles, location FROM Run WHERE id = :id")
                                .param("id", id)
                                .query(Run.class)
                                .optional();

        }

        public void create(Run run) {
                var update = jdbcClient.sql(
                                "INSERT INTO Run (id, title, started_on, completed_on, miles, location) VALUES (:id, :title, :started_on, :completed_on, :miles, :location)")
                                .param("id", run.id())
                                .param("title", run.title())
                                .param("started_on", run.startedOn())
                                .param("completed_on", run.completedOn())
                                .param("miles", run.miles())
                                .param("location", run.location().toString())
                                .update();

                Assert.state(update == 1, "Failed to create Run" + run.title());
        }

        public void update(Run run, Integer id) {
                var update = jdbcClient.sql(
                                "UPDATE Run SET title = :title, started_on = :started_on, completed_on = :completed_on, miles = :miles, location = :location WHERE id = :id")
                                .param("title", run.title())
                                .param("started_on", run.startedOn())
                                .param("completed_on", run.completedOn())
                                .param("miles", run.miles())
                                .param("location", run.location().toString())
                                .param("id", id)
                                .update();

                Assert.state(update == 1, "Failed to update run " + run.title());
        }

        public void delete(Integer id) {
                var update = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                                .param("id", id)
                                .update();
                Assert.state(update == 1, "Failed to delete Run with id " + id);
        }

        public int count() {
                return jdbcClient.sql("SELECT * FROM Run").query().listOfRows().size();
        }

        public void saveAll(List<Run> runs) {
                runs.stream().forEach(this::create);
        }

        public List<Run> findByLocation(String location) {
                return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
                                .param(location)
                                .query(Run.class)
                                .list();
        }
}
