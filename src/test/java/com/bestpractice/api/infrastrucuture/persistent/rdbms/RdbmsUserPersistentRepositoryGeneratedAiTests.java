package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private JdbcTemplate jdbcTemplate;
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidUUID() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "username", "email@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), any()))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail("email@example.com");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser, result);
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "username", "email@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), any()))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findById("1");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser, result);
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object.class), any(Object.class), any(Object.class), any(Object.class))).thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenDuplicateKey_whenInsert_thenThrowsConflict() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object.class), any(Object.class), any(Object.class), any(Object.class)))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object.class), any(Object.class), any(Object.class), any(Object.class))).thenReturn(1);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenExistingId_whenRemoveById_thenReturnsTrue() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertTrue(result);
    }

    @Test
    void givenException_whenRemoveById_thenReturnsFalse() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenThrow(new RuntimeException("error"));

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertFalse(result);
    }
}
