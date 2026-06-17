package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;

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
    void newId_ShouldReturnValidUUID() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "john", "john@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), any(Object[].class)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail("john@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "john", "john@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), any(Object[].class)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(anyString(), any(Object[].class));
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user)).isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldUpdateUserSuccessfully() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(anyString(), any(Object[].class));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeletionSucceeds() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(anyString(), any(Object[].class));
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionOccurs() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenThrow(new RuntimeException("error"));

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
