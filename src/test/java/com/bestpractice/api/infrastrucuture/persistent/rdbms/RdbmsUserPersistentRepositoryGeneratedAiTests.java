package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
        Mockito.reset(jdbcTemplate);
    }

    @Test
    void testNewId() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isNotNull();
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "alice", "alice@example.com", "secret");
        when(jdbcTemplate.queryForObject(anyString(), any(), anyString())).thenReturn(expectedUser);
        // WHEN
        User actualUser = repository.findByEmail("alice@example.com");
        // THEN
        assertThat(actualUser).isSameAs(expectedUser);
        verify(jdbcTemplate, times(1)).queryForObject(
                eq("SELECT * FROM users WHERE email = ?"),
                any(),
                eq("alice@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "bob", "bob@example.com", "secret");
        when(jdbcTemplate.queryForObject(anyString(), any(), anyString())).thenReturn(expectedUser);
        // WHEN
        User actualUser = repository.findById("1");
        // THEN
        assertThat(actualUser).isSameAs(expectedUser);
        verify(jdbcTemplate, times(1)).queryForObject(
                eq("SELECT * FROM users WHERE id = ?"),
                any(),
                eq("1"));
    }

    @Test
    void testInsertCallsJdbcTemplateUpdate() {
        // GIVEN
        User user = new User("2", "carol", "carol@example.com", "pwd");
        // WHEN
        User returnedUser = repository.insert(user);
        // THEN
        assertThat(returnedUser).isSameAs(user);
        ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                captor.capture());
        Object[] args = captor.getValue();
        assertThat(args).containsExactly(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN
        User user = new User("3", "dave", "dave@example.com", "pwd");
        doThrow(new DuplicateKeyException("duplicate")).when(jdbcTemplate).update(anyString(), any());
        // WHEN
        Conflict thrown = assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .extracting(Throwable::getCause)
                .isInstanceOf(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .get();
        // THEN
        assertThat(thrown).isNotNull();
    }

    @Test
    void testReplaceCallsJdbcTemplateUpdate() {
        // GIVEN
        User user = new User("4", "eve", "eve@example.com", "pwd");
        // WHEN
        User returnedUser = repository.replace("4", user);
        // THEN
        assertThat(returnedUser).isSameAs(user);
        verify(jdbcTemplate, times(1)).update(
                eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"),
                user.getUsername(), user.getEmail(), user.getPassword(), "4");
    }

    @Test
    void testRemoveByIdReturnsTrueWhenSuccessful() {
        // GIVEN
        // WHEN
        boolean result = repository.removeById("5");
        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(
                eq("DELETE FROM users WHERE id = ?"),
                eq("5"));
    }

    @Test
    void testRemoveByIdReturnsFalseWhenException() {
        // GIVEN
        doThrow(new RuntimeException("db error")).when(jdbcTemplate).update(anyString(), any());
        // WHEN
        boolean result = repository.removeById("6");
        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(
                eq("DELETE FROM users WHERE id = ?"),
                eq("6"));
    }
}
