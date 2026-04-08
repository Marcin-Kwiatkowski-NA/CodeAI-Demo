package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private RdbmsUserPersistentRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
        user = new User("1", "testUser", "test@example.com", "password123");
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
        when(jdbcTemplate.queryForObject(anyString(), any(), anyString())).thenReturn(user);

        // WHEN
        User result = repository.findByEmail("test@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(), anyString())).thenReturn(user);

        // WHEN
        User result = repository.findById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    void insert_ShouldReturnUser_WhenInsertSuccessful() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testUser");
        verify(jdbcTemplate, times(1)).update(anyString(), any());
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldReturnUser_WhenUpdateSuccessful() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testUser");
        verify(jdbcTemplate, times(1)).update(anyString(), any());
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeleteSuccessful() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(anyString(), any());
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionThrown() {
        // GIVEN
        doThrow(new RuntimeException("error")).when(jdbcTemplate).update(anyString(), any());

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
