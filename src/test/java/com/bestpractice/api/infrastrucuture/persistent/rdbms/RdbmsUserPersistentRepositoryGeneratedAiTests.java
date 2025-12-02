package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
        User expectedUser = new User("1", "testUser", email, "password");
        when(jdbcTemplate.queryForObject(any(String.class), any(DataClassRowMapper.class), eq(email)))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail(email);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
        verify(jdbcTemplate, times(1)).queryForObject(any(String.class), any(DataClassRowMapper.class), eq(email));
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(jdbcTemplate.queryForObject(any(String.class), any(DataClassRowMapper.class), eq(id)))
                .thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById(id);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
        verify(jdbcTemplate, times(1)).queryForObject(any(String.class), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void insert_ShouldInsertUser_WhenUserIsValid() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doNothing().when(jdbcTemplate).update(any(String.class), eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(any(String.class), eq(user.getId()), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()));
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(any(String.class), any(), any(), any(), any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(org.springframework.dao.DuplicateKeyException.class);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(), any(), any(), any());
    }

    @Test
    void replace_ShouldUpdateUser_WhenUserExists() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        doNothing().when(jdbcTemplate).update(any(String.class), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()), eq(id));

        // WHEN
        User updatedUser = repository.replace(id, user);

        // THEN
        assertThat(updatedUser).isEqualTo(user);
        verify(jdbcTemplate, times(1)).update(any(String.class), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPassword()), eq(id));
    }

@Test
    void removeById_ShouldReturnTrue_WhenUserIsDeleted() {
        // GIVEN
        String id = "1";
        doNothing().when(jdbcTemplate).update(any(String.class), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(any(String.class), eq(id));
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionOccurs() {
        // GIVEN
        String id = "1";
        doThrow(new RuntimeException("Deletion failed")).when(jdbcTemplate).update(any(String.class), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(any(String.class), eq(id));
    }
}
