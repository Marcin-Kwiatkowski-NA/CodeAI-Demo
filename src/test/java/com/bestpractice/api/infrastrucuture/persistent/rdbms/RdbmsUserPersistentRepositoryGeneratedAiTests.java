package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

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
    void newIdShouldReturnUuidString() {
        // GIVEN
        // (no additional setup)

        // WHEN
        String id = repository.newId();

        // THEN
        Assertions.assertThat(id)
                .isNotNull()
                .matches("^[0-9a-fA-F-]{36}$", "UUID format");
    }

    @Test
    void findByEmailShouldReturnUser() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User("1", "testuser", email, "password");
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.eq(email)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        Assertions.assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void findByIdShouldReturnUser() {
        // GIVEN
        String id = "1";
        User expectedUser = new User(id, "testuser", "test@example.com", "password");
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.eq(id)))
                .thenReturn(expectedUser);

        // WHEN
        User result = repository.findById(id);

        // THEN
        Assertions.assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void insertShouldPersistUserAndReturnIt() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any()))
                .thenReturn(1);

        // WHEN
        User result = repository.insert(user);

        // THEN
        Assertions.assertThat(result).isEqualTo(user);
        ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        Mockito.verify(jdbcTemplate).update(
                Mockito.anyString(),
                captor.capture());
        Object[] args = captor.getValue();
        Assertions.assertThat(args).containsExactly(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Test
    void insertShouldThrowConflictWhenDuplicateKey() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any()))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replaceShouldUpdateAndReturnUser() {
        // GIVEN
        String id = "1";
        User user = new User(id, "newuser", "new@example.com", "newpass");
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any()))
                .thenReturn(1);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        Assertions.assertThat(result).isEqualTo(user);
        ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        Mockito.verify(jdbcTemplate).update(
                Mockito.anyString(),
                captor.capture());
        Object[] args = captor.getValue();
        Assertions.assertThat(args).containsExactly(user.getUsername(), user.getEmail(), user.getPassword(), id);
    }

    @Test
    void removeByIdShouldReturnTrueWhenDeletionSucceeds() {
        // GIVEN
        String id = "1";
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.eq(id))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isTrue();
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.eq(id));
    }

    @Test
    void removeByIdShouldReturnFalseWhenDeletionFails() {
        // GIVEN
        String id = "1";
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.eq(id)))
                .thenThrow(new RuntimeException("db error"));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isFalse();
    }
}
