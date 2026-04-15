package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testUser", "test@example.com", "password123");
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
    void insert_ShouldReturnUser_WhenInsertSucceeds() {
        // GIVEN
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
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldReturnUser_WhenUpdateSucceeds() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(anyString(), any(Object[].class));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeleteSucceeds() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionOccurs() {
        // GIVEN
        doThrow(new RuntimeException("error")).when(jdbcTemplate).update(anyString(), any(Object[].class));

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
