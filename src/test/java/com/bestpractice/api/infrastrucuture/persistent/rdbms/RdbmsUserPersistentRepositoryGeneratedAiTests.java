package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        // GIVEN - repository initialized

        // WHEN - generating new ID
        String id = repository.newId();

        // THEN - ID should not be null and should be a valid UUID
        assertNotNull(id);
        assertDoesNotThrow(() -> java.util.UUID.fromString(id));
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN - mock returns a user
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("test@example.com")))
                .thenReturn(expectedUser);

        // WHEN - finding by email
        User actualUser = repository.findByEmail("test@example.com");

        // THEN - returned user matches expected
        assertNotNull(actualUser);
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    void testFindByEmailThrowsRuntimeException() {
        // GIVEN - mock throws RuntimeException
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("test@example.com")))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - finding by email throws RuntimeException
        assertThrows(RuntimeException.class, () -> repository.findByEmail("test@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN - mock returns a user
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("1")))
                .thenReturn(expectedUser);

        // WHEN - finding by ID
        User actualUser = repository.findById("1");

        // THEN - returned user matches expected
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    void testFindByIdThrowsRuntimeException() {
        // GIVEN - mock throws RuntimeException
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("1")))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - finding by ID throws RuntimeException
        assertThrows(RuntimeException.class, () -> repository.findById("1"));
    }

    @Test
    void testInsertUserSuccess() {
        // GIVEN - mock update succeeds
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - inserting user
        User insertedUser = repository.insert(user);

        // THEN - returned user matches input
        assertNotNull(insertedUser);
        assertEquals(user.getId(), insertedUser.getId());
    }

    @Test
    void testInsertUserDuplicateKeyThrowsConflict() {
        // GIVEN - mock update throws DuplicateKeyException
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN - inserting user throws Conflict
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    void testInsertUserThrowsRuntimeException() {
        // GIVEN - mock update throws RuntimeException
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - inserting user throws RuntimeException
        assertThrows(RuntimeException.class, () -> repository.insert(user));
    }

    @Test
    void testReplaceUserSuccess() {
        // GIVEN - mock update succeeds
        User user = new User("1", "updateduser", "updated@example.com","newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - replacing user
        User replacedUser = repository.replace("1", user);

        // THEN - returned user matches input
        assertNotNull(replacedUser);
        assertEquals(user.getUsername(), replacedUser.getUsername());
    }

    @Test
    void testReplaceUserThrowsRuntimeException() {
        // GIVEN - mock update throws RuntimeException
        User user = new User("1", "updateduser", "updated@example.com", "newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - replacing user throws RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("1", user));
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN - mock update succeeds
        when(jdbcTemplate.update(anyString(), eq("1"))).thenReturn(1);

        // WHEN - removing user by ID
        boolean result = repository.removeById("1");

        // THEN - result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN - mock update throws exception
        when(jdbcTemplate.update(anyString(), eq("1"))).thenThrow(new RuntimeException("error"));

        // WHEN - removing user by ID
        boolean result = repository.removeById("1");

        // THEN - result should be false
        assertFalse(result);
    }
}
