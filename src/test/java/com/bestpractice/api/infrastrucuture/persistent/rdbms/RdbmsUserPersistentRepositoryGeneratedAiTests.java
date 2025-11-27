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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
        User mockUser = new User("1", "testUser", email, "password123");
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email)))
                .thenReturn(mockUser);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email));
    }

    @Test
    void findByEmail_ShouldThrowException_WhenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE email = ?"), any(DataClassRowMapper.class), eq(email));
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = "1";
        User mockUser = new User(id, "testUser", "test@example.com", "password123");
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id)))
                .thenReturn(mockUser);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        // GIVEN
        String id = "nonexistentId";
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id)))
                .thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM users WHERE id = ?"), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void insert_ShouldInsertUser_WhenValidUserProvided() {
        // GIVEN
        User user = new User("1","testUser", "test@example.com", "password123");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user.getId());
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"), 
            eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));
    }

    @Test
    void insert_ShouldThrowConflictException_WhenDuplicateKeyOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        doThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key")).when(jdbcTemplate)
                .update(anyString(), any(), any(), any(), any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(org.springframework.dao.DuplicateKeyException.class);
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)"), 
            eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));
    }

    @Test
    void replace_ShouldUpdateUser_WhenValidIdAndUserProvided() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        verify(jdbcTemplate, times(1)).update(eq("UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"), 
            eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()), eq(id));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenUserIsDeletedSuccessfully() {
        // GIVEN
        String id = "1";
        doNothing().when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM users WHERE id = ?"), eq(id));
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
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM users WHERE id = ?"), eq(id));
    }
}