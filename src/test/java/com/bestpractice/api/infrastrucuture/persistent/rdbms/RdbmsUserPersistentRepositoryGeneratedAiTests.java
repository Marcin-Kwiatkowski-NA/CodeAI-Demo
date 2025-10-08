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
        // GIVEN - repository is initialized

        // WHEN - generating a new ID
        String id = repository.newId();

        // THEN - ID should not be null and should be a valid UUID
        assertNotNull(id);
        assertDoesNotThrow(() -> java.util.UUID.fromString(id));
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN - a user exists with given email
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("test@example.com")))
                .thenReturn(expectedUser);

        // WHEN - finding user by email
        User actualUser = repository.findByEmail("test@example.com");

        // THEN - returned user should match expected
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testFindByEmailThrowsRuntimeException() {
        // GIVEN - jdbcTemplate throws RuntimeException
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - finding user by email should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.findByEmail("error@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN - a user exists with given id
        User expectedUser = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("1")))
                .thenReturn(expectedUser);

        // WHEN - finding user by id
        User actualUser = repository.findById("1");

        // THEN - returned user should match expected
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testFindByIdThrowsRuntimeException() {
        // GIVEN - jdbcTemplate throws RuntimeException
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString()))
                .thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - finding user by id should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.findById("errorId"));
    }

    @Test
    void testInsertUserSuccess() {
        // GIVEN - a user to insert
        User user = new User("1", "testuser", "test@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - inserting user
        User insertedUser = repository.insert(user);

        // THEN - returned user should be same as inserted
        assertEquals(user, insertedUser);
    }

    @Test
    void testInsertUserDuplicateKeyThrowsConflict() {
        // GIVEN - a user to insert that causes duplicate key
        User user = new User("1", "testuser", "test@example.com", "pass");
        doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate)
                .update(anyString(), any(), any(), any(), any());

        // WHEN & THEN - inserting should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    void testInsertUserThrowsRuntimeException() {
        // GIVEN - a user to insert that causes generic exception
        User user = new User("1", "testuser", "test@example.com", "pass");
        doThrow(new RuntimeException("DB error")).when(jdbcTemplate)
                .update(anyString(), any(), any(), any(), any());

        // WHEN & THEN - inserting should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.insert(user));
    }

    @Test
    void testReplaceUserSuccess() {
        // GIVEN - a user to replace
        User user = new User("1", "updateduser", "updated@example.com", "newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - replacing user
        User replacedUser = repository.replace("1", user);

        // THEN - returned user should be same as replaced
        assertEquals(user, replacedUser);
    }

    @Test
    void testReplaceUserThrowsRuntimeException() {
        // GIVEN - a user to replace that causes exception
        User user = new User("1", "updateduser", "updated@example.com", "newpass");
        doThrow(new RuntimeException("DB error")).when(jdbcTemplate)
                .update(anyString(), any(), any(), any(), any());

        // WHEN & THEN - replacing should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("1", user));
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN - a user id to remove
        when(jdbcTemplate.update(anyString(), eq("1"))).thenReturn(1);

        // WHEN - removing user by id
        boolean result = repository.removeById("1");

        // THEN - result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailureReturnsFalse() {
        // GIVEN - a user id to remove that causes exception
        doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(anyString(), eq("1"));

        // WHEN - removing user by id
        boolean result = repository.removeById("1");

        // THEN - result should be false
        assertFalse(result);
    }

    @Test
    void testRemoveByIdThrowsRuntimeExceptionAndReturnsFalse() {
        // GIVEN - jdbcTemplate throws exception
        doThrow(new RuntimeException("DB error")).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN - removing user by id
        boolean result = repository.removeById("errorId");

        // THEN - result should be false
        assertFalse(result);
    }
}
