package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    void setUp() {
        jdbcTemplate = new JdbcTemplate();
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
    }

    void newId() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assert id != null;
    }

    void findAll() {
        // GIVEN
        // WHEN
        var list = repository.findAll();
        // THEN
        assert list != null;
    }

    void findById(String id) {
        // GIVEN
        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN
        Info foundInfo = repository.findById(id);
        // THEN
        assert foundInfo != null;
    }

    void insert() {
        // GIVEN
        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN
        info = repository.insert(info);
        // THEN
        assert info.getId() != null;
    }

    void replace(String id, Info info) {
        // GIVEN
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN
        info = repository.replace(id, info);
        // THEN
        assert info.getId() != null;
    }

    void removeById(String id) {
        // GIVEN
        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN
        boolean removed = repository.removeById(id);
        // THEN
        assert removed;
    }
}
