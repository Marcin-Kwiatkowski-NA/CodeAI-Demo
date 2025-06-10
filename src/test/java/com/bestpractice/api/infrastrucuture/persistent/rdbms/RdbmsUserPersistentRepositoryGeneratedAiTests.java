package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
    }

    @Test
    public void testNewId() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull();
    }

    @Test
    public void testFindByEmail() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString()))
                .thenReturn(user);
        // WHEN
        User foundUser = repository.findByEmail("email@example.com");
        // THEN
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    public void testFindById() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString()))
                .thenReturn(user);
        // WHEN
        User foundUser = repository.findById(user.getId());
        // THEN
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    public void testInsert() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), any(Object[].class));
        // WHEN
        User insertedUser = repository.insert(user);
        // THEN
        assertThat(insertedUser).isEqualTo(user);
    }

    @Test
    public void testInsertConflict() {
        // GIVEN
        doThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key")).when(jdbcTemplate)
                .update(anyString(), any(Object[].class));
        // WHEN & THEN
        assertThrows(Conflict.class, () -> repository.insert(user));
    }

    @Test
    public void testReplace() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), any(Object[].class));
        // WHEN
        User replacedUser = repository.replace(user.getId(), user);
        // THEN
        assertThat(replacedUser).isEqualTo(user);
    }

    @Test
    public void testRemoveById() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), any(Object[].class));
        // WHEN
        boolean result = repository.removeById(user.getId());
        // THEN
        assertThat(result).isTrue();
    }

    @Test
    public void testRemoveByIdException() {
        // GIVEN
        doThrow(new RuntimeException()).when(jdbcTemplate).update(anyString(), any(Object[].class));
        // WHEN
        boolean result = repository.removeById(user.getId());
        // THEN
        assertThat(result).isFalse();
    }
}