package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        reset(jdbcTemplate);
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
        String email = "test@example.com";
        User expectedUser = new User("1", "user", email, "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(), eq(email)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
        verify(jdbcTemplate).queryForObject(anyString(), any(), eq(email));
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        String id = "123";
        User expectedUser = new User(id, "user", "email@example.com", "pass");
        when(jdbcTemplate.queryForObject(anyString(), any(), eq(id)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
        verify(jdbcTemplate).queryForObject(anyString(), any(), eq(id));
    }

    @Test
    void insert_ShouldReturnUser_WhenInsertSucceeds() {
        // GIVEN
        User user = new User("1", "user", "email@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate).update(anyString(), any(), any(), any(), any());
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new User("1", "user", "email@example.com", "pass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldReturnUser_WhenUpdateSucceeds() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "newpass");
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(jdbcTemplate).update(anyString(), any(), any(), any(), eq(id));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeleteSucceeds() {
        // GIVEN
        String id = "1";
        when(jdbcTemplate.update(anyString(), eq(id))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate).update(anyString(), eq(id));
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionOccurs() {
        // GIVEN
        String id = "1";
        doThrow(new RuntimeException("error")).when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate).update(anyString(), eq(id));
    }
}
