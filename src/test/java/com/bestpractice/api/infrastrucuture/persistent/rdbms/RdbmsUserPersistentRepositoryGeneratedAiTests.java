package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isInstanceOf(String.class);
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User("1", "testUser", email, "password");
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email)))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail(email);

        // THEN
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getEmail()).isEqualTo(email);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email));
    }

    @Test
    void findByEmail_shouldThrowException_whenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id)))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById(id);

        // THEN
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getId()).isEqualTo(id);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void findById_shouldThrowException_whenIdDoesNotExist() {
        // GIVEN
        String id = "nonexistentId";
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo("1");
        verify(jdbcTemplate, times(1)).update(anyString(), eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));
    }

    @Test
    void insert_shouldThrowConflictException_whenDuplicateKeyOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_shouldUpdateUserSuccessfully() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), eq(id));

        // WHEN
        User updatedUser = repository.replace(id, user);

        // THEN
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(id);
        assertThat(updatedUser.getUsername()).isEqualTo("updatedUser");
        verify(jdbcTemplate, times(1)).update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()), eq(id));
    }

    @Test
    void removeById_shouldReturnTrue_whenDeletionIsSuccessful() {
        // GIVEN
        String id = "1";
        doNothing().when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(anyString(), eq(id));
    }

    @Test
    void removeById_shouldReturnFalse_whenDeletionFails() {
        // GIVEN
        String id = "nonexistentId";
        doThrow(new RuntimeException("Deletion failed")).when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(anyString(), eq(id));
    }
}
