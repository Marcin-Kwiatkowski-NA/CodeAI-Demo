package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
    void newId_shouldGenerateUniqueId() {
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
    void findByEmail_shouldReturnUser_whenEmailExists() {
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
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_shouldThrowException_whenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE email = ?"),
                any(DataClassRowMapper.class),
                eq(email)
        )).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
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
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findById_shouldThrowException_whenIdDoesNotExist() {
        // GIVEN
        String id = "nonexistentId";
        Mockito.when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = ?"),
                any(DataClassRowMapper.class),
                eq(id)
        )).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        Mockito.doAnswer(invocation -> null).when(jdbcTemplate).update(
                eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                eq(user.getId()),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword())
        );

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isEqualTo(user);
    }

    @Test
    void insert_shouldThrowConflictException_whenDuplicateKeyOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        Mockito.doThrow(new DuplicateKeyException("Duplicate key"))
                .when(jdbcTemplate).update(
                        eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"),
                        eq(user.getId()),
                        eq(user.getUsername()),
                        eq(user.getEmail()),
                        eq(user.getPassword                );

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
        Mockito.doAnswer(invocation -> null).when(jdbcTemplate).update(
                eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"),
                eq(user.getUsername()),
                eq(user.getEmail()),
                eq(user.getPassword()),
                eq(id)
        );

        // WHEN
        User updatedUser = repository.replace(id, user);

        // THEN
        assertThat(updatedUser).isEqualTo(user);
    }

    @Test
    void removeById_shouldReturnTrue_whenUserIsDeletedSuccessfully() {
        // GIVEN
        String id = "1";
        Mockito.doAnswer(invocation -> null).when(jdbcTemplate).update(
                eq("DELETE FROM users WHERE id = ?"),
                eq(id)
        );

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldReturnFalse_whenDeletionFails() {
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
