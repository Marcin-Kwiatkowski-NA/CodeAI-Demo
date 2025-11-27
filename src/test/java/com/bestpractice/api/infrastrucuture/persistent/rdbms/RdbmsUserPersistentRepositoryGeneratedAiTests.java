package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
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
    void newId_ShouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User("1", "testUser", email, "password123");
        String sql = "SELECT * FROM users WHERE email = ?";
        when(jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), email)).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail(email);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_ShouldThrowException_WhenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        String sql = "SELECT * FROM users WHERE email = ?";
        when(jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), email)).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testUser", "test@example.com", "password123");
        String sql = "SELECT * FROM users WHERE id = ?";
        when(jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), id)).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById(id);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        // GIVEN
        String id = "nonexistentId";
        String sql = "SELECT * FROM users WHERE id = ?";
        when(jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), id)).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void insert_ShouldInsertUser_WhenUserIsValid() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";

        // WHEN
        repository.insert(user);

        // THEN
        verify(jdbcTemplate, times(1)).update(sql, user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
        doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(sql, user.getId(), user.getUsername(), user.getEmail(), user.getPassword());

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_ShouldUpdateUser_WhenUserExists() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword123");
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

        // WHEN
        repository.replace(id, user);

        // THEN
        verify(jdbcTemplate, times(1)).update(sql, user.getUsername(), user.getEmail(), user.getPassword(), id);
    }

    @Test
    void removeById_ShouldReturnTrue_WhenUserIsDeletedSuccessfully() {
        // GIVEN
        String id = "1";
        String sql = "DELETE FROM users WHERE id = ?";
        when(jdbcTemplate.update(sql, id)).thenReturn(1);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(sql, id);
    }

    @Test
    void removeById_ShouldReturnFalse_WhenDeletionFails() {
        // GIVEN
        String id = "nonexistentId";
        String sql = "DELETE FROM users WHERE id = ?";
        doThrow(new RuntimeException("Deletion failed")).when(jdbcTemplate).update(sql, id);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(sql, id);
    }
}