package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;

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
        // No preconditions required

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).hasSize(36); // UUID length
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User("1", "testUser", email, "password");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq(email)))
                .thenReturn(expectedUser);

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
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq(email)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq(id)))
                .thenReturn(expectedUser);

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
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq(id)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
    }

    @Test
    void insert_ShouldInsertUser_WhenUserIsValid() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        verify(jdbcTemplate, times(1)).update(anyString(), eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key"))
                .when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldUpdateUser_WhenUserExists() {
        // GIVEN
        String id = "1";
        User user = new User(id, "testUser", "updated@example.com", "newPassword");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), eq(id));

        // WHEN
        User updatedUser = repository.replace(id, user);

        // THEN
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(id);
        assertThat(updatedUser.getEmail()).isEqualTo(user.getEmail());
        verify(jdbcTemplate, times(1)).update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()), eq(id));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenUserIsDeleted() {
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
    void removeById_ShouldReturnFalse_WhenDeletionFails() {
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
