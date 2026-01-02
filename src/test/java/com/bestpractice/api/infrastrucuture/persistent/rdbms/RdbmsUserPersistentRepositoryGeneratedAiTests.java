package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(PowerMockExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private JdbcTemplate jdbcTemplate;
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = PowerMockito.mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_ShouldReturnNonNullAndUnique() {
        // GIVEN
        // (none)

        // WHEN
        String firstId = repository.newId();
        String secondId = repository.newId();

        // THEN
        assertThat(firstId).isNotNull();
        assertThat(secondId).isNotNull();
        assertThat(firstId).isNotEqualTo(secondId);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        User expectedUser = new User("1", "john", "john@example.com", "pass");
        PowerMockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE email = ?"),
                eq(new org.springframework.jdbc.core.DataClassRowMapper<>(User.class)),
                eq("john@example.com")))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail("john@example.com");

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        User expectedUser = new User("1", "john", "john@example.com", "pass");
        PowerMockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = ?"),
                eq(new org.springframework.jdbc.core.DataClassRowMapper<>(User.class)),
                eq("1")))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById("1");

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void insert_ShouldCallJdbcTemplateUpdateAndReturnUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        PowerMockito.when(jdbcTemplate.update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                eq(user.getId()),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()))).thenReturn(1);

        // WHEN
        User returnedUser = repository.insert(user);

        // THEN
        assertThat(returnedUser).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                eq(user.getId()),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()));
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyException() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        PowerMockito.when(jdbcTemplate.update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                eq(user.getId()),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_ShouldCallJdbcTemplateUpdateAndReturnUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        PowerMockito.when(jdbcTemplate.update(
                eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()),
                eq("1"))).thenReturn(1);

        // WHEN
        User returnedUser = repository.replace("1", user);

        // THEN
        assertThat(returnedUser).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()),
                eq("1"));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeleteSuccessful() {
        // GIVEN
        PowerMockito.when(jdbcTemplate.update(eq("DELETE FROM users WHERE id = ?"), eq("1"))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionThrown() {
        // GIVEN
        PowerMockito.when(jdbcTemplate.update(eq("DELETE FROM users WHERE id = ?"), eq("1")))
                .thenThrow(new RuntimeException("db error"));

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
