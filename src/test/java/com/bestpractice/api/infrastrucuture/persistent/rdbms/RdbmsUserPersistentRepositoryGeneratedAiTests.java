package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

@Test
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate();
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId() {
        String id = repository.newId();
        assert id != null;
    }

    @Test
    void findByEmail() {
        User user = repository.findByEmail("test@example.com");
        assert user != null;
        assert user.getEmail().equals("test@example.com");
    }

    @Test
    void findById() {
        User user = repository.findById("123");
        assert user != null;
        assert user.getId().equals("123");
    }

    @Test
    void insert() {
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        User insertedUser = repository.insert(user);
        assert insertedUser != null;
        assert insertedUser.getId() != null;
    }

    @Test
    void replace() {
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "newPassword");
        User replacedUser = repository.replace("123", user);
        assert replacedUser != null;
        assert replacedUser.getUsername().equals("testUser");
        assert replacedUser.getEmail().equals("test@example.com");
    }

    @Test
    void removeById() {
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        boolean deleted = repository.removeById("123");
        assert deleted;
    }
}
