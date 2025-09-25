package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DuplicateKeyException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private JdbcTemplate jdbcTemplate;
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    public void testNewIdGeneratesUUID() {
        // GIVEN - repository initialized

        // WHEN - generating new ID
        String id = repository.newId();

        // THEN - ID should be a valid UUID
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    public void testFindByEmailReturnsUser() {
        // GIVEN - mock returns a user
        User expectedUser = new User("1", "username", "email@example.com", "password");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("email@example.com")))
                .thenReturn(expectedUser);

        // WHEN - finding by email
        User actualUser = repository.findByEmail("email@example.com");

        // THEN - returned user matches expected
        assertNotNull(actualUser);
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    public void testFindByIdReturnsUser() {
        // GIVEN - mock returns a user
        User expectedUser = new User("1", "username", "email@example.com", "password");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("1")))
                .thenReturn(expectedUser);

        // WHEN - finding by ID
        User actualUser = repository.findById("1");

        // THEN - returned user matches expected
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    public void testInsertUserSuccess() {
        // GIVEN - mock update succeeds
        User user = new User("1", "username", "email@example.com", "password");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - inserting user
        User insertedUser = repository.insert(user);

        // THEN - returned user matches input
        assertNotNull(insertedUser);
        assertEquals(user.getId(), insertedUser.getId());
    }

    @Test
    public void testInsertUserDuplicateKeyThrowsConflict() {
        // GIVEN - mock update throws DuplicateKeyException
        User user = new User("1", "username", "email@example.com", "password");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN - inserting user throws Conflict
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    public void testReplaceUserSuccess() {
        // GIVEN - mock update succeeds
        User user = new User("1", "newUsername", "newEmail@example.com", "newPassword");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN - replacing user
        User replacedUser = repository.replace("1", user);

        // THEN - returned user matches input
        assertNotNull(replacedUser);
        assertEquals(user.getUsername(), replacedUser.getUsername());
    }

    @Test
    public void testRemoveByIdSuccess() {
        // GIVEN - mock update succeeds
        when(jdbcTemplate.update(anyString(), eq("1"))).thenReturn(1);

        // WHEN - removing user by ID
        boolean result = repository.removeById("1");

        // THEN - result should be true
        assertTrue(result);
    }

    @Test
    public void testRemoveByIdFailure() {
        // GIVEN - mock update throws exception
        when(jdbcTemplate.update(anyString(), eq("1"))).thenThrow(new RuntimeException("error"));

        // WHEN - removing user by ID
        boolean result = repository.removeById("1");

        // THEN - result should be false
        assertFalse(result);
    }
}
