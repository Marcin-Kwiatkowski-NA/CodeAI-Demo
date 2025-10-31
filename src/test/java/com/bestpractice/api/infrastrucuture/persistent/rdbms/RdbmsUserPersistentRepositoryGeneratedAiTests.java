package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private JdbcTemplate jdbcTemplate;
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void testNewIdGeneratesUUID() {
        // GIVEN - no preconditions

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("test@example.com")))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail("test@example.com");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser.getEmail(), result.getEmail());
    }

    @Test
    void testFindByEmailThrowsRuntimeException() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("missing@example.com")))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> repository.findByEmail("missing@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("1")))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findById("1");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
    }

    @Test
    void testFindByIdThrowsRuntimeException() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("999")))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> repository.findById("999"));
    }

    @Test
    void testInsertUserSuccess() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void testInsertUserDuplicateKeyThrowsConflict() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new DuplicateKeyException("Duplicate"));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    void testInsertThrowsRuntimeException() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> repository.insert(user));
    }

    @Test
    void testReplaceUserSuccess() {
        // GIVEN
        User user = new User("1", "updateduser", "updated@example.com", "newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testReplaceThrowsRuntimeException() {
        // GIVEN
        User user = new User("1", "updateduser", "updated@example.com", "newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> repository.replace("1", user));
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), eq("1"))).thenReturn(1);

        // WHEN
        boolean removed = repository.removeById("1");

        // THEN
        assertTrue(removed);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), eq("1"))).thenThrow(new RuntimeException("DB error"));

        // WHEN
        boolean removed = repository.removeById("1");

        // THEN
        assertFalse(removed);
    }
}
