package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(jdbcTemplate);
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
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE email = ?"),
                any(DataClassRowMapper.class),
                eq(email)
        )).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail(email);

        // THEN
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getEmail()).isEqualTo(email);
        assertThat(actualUser.getUsername()).isEqualTo(expectedUser.getUsername());
    }

    @Test
    void findByEmail_ShouldThrowException_WhenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE email = ?"),
                any(DataClassRowMapper.class),
                eq(email)
        )).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testUser", "test@example.com", "password123");
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = ?"),
                any(DataClassRowMapper.class),
                eq(id)
        )).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById(id);

        // THEN
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getId()).isEqualTo(id);
        assertThat(actualUser.getUsername()).isEqualTo(expectedUser.getUsername());
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        // GIVEN
        String id = "nonexistentId";
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = ?"),
                any(DataClassRowMapper.class),
                eq(id)
        )).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void insert_ShouldInsertUser_WhenUserIsValid() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        Mockito.doNothing().when(jdbcTemplate).update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                eq(user.getId()),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword())
        );

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        assertThat(insertedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new        User user = new User("1", "testUser", "test@example.com", "password123");
        Mockito.doThrow(new DuplicateKeyException("Duplicate key"))
                .when(jdbcTemplate).update(
                        eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                        eq(user.getId()),
                        eq(user.getUsername()),
                        eq(user.getEmail()),
                        eq(user.getPassword())
                );

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_ShouldUpdateUser_WhenUserExists() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword123");
        Mockito.doNothing().when(jdbcTemplate).update(
                eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()),
                eq(id)
        );

        // WHEN
        User updatedUser = repository.replace(id, user);

        // THEN
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(updatedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void removeById_ShouldReturnTrue_WhenUserIsDeleted() {
        // GIVEN
        String id = "1";
        Mockito.doNothing().when(jdbcTemplate).update(
                eq("DELETE FROM users WHERE id = ?"),
                eq(id)
        );

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldReturnFalse_WhenDeletionFails() {
        // GIVEN
        String id = "nonexistentId";
        Mockito.doThrow(new RuntimeException("Deletion failed"))
                .when(jdbcTemplate).update(
                        eq("DELETE FROM users WHERE id = ?"),
                        eq(id)
                );

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }
}
