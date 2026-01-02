package com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        reset(jdbcTemplate);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void testNewIdGeneratesUUID() {
        // GIVEN
        // (no precondition needed)

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).matches("^[0-9a-fA-F-]{36}$");
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "alice", "alice@example.com", "secret");
        when(jdbcTemplate.queryForObject(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(DataClassRowMapper.class),
                ArgumentMatchers.eq("alice@example.com")))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail("alice@example.com");

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
        verify(jdbcTemplate).queryForObject(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(DataClassRowMapper.class),
                ArgumentMatchers.eq("alice@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "bob", "bob@example.com", "secret");
        when(jdbcTemplate.queryForObject(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(DataClassRowMapper.class),
                ArgumentMatchers.eq("1")))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById("1");

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
        verify(jdbcTemplate).queryForObject(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(DataClassRowMapper.class),
                ArgumentMatchers.eq("1"));
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        User user = new User("2", "carol", "carol@example.com", "secret");
        when(jdbcTemplate.update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getId()),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword())))
                .thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate).update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getId()),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword()));
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN
        User user = new User("3", "dave", "dave@example.com", "secret");
        when(jdbcTemplate.update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getId()),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword())))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
        verify(jdbcTemplate).update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getId()),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword()));
    }

    @Test
    void testReplaceUpdatesUser() {
        // GIVEN
        User user = new User("4", "eve", "eve@example.com", "secret");
        when(jdbcTemplate.update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword()),
                ArgumentMatchers.eq("4")))
                .thenReturn(1);

        // WHEN
        User result = repository.replace("4", user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate).update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(user.getUsername()),
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq(user.getPassword()),
                ArgumentMatchers.eq("4"));
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN
        when(jdbcTemplate.update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq("5")))
                .thenReturn(1);

        // WHEN
        boolean success = repository.removeById("5");

        // THEN
        assertThat(success).isTrue();
        verify(jdbcTemplate).update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq("5"));
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN
        when(jdbcTemplate.update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq("6")))
                .thenThrow(new RuntimeException("db error"));

        // WHEN
        boolean success = repository.removeById("6");

        // THEN
        assertThat(success).isFalse();
        verify(jdbcTemplate).update(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq("6"));
    }
}
