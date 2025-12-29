package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserPersistentRepository repository;

    private ArgumentCaptor<String> idCaptor;

    private ArgumentCaptor<String> usernameCaptor;

    private ArgumentCaptor<String> emailCaptor;

    private ArgumentCaptor<String> passwordCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new UserPersistentRepository(jdbcTemplate);
    }

    @Test
    void shouldGenerateNewId() {
        // GIVEN
        // WHEN
        String generatedId = repository.newId();

        // THEN
        assertThat(generatedId).isNotNull();
        assertThat(generatedId).isNotEqualTo("");
        assertThat(generatedId).hasSize(36);
    }

    @Test
    void shouldFindUserByEmail() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User(UUID.randomUUID().toString(), "username", email, "password");
        when(jdbcTemplate.queryForObject(any(String.class), any(DataClassRowMapper.class), any(String.class)))
            .thenReturn(expectedUser);

        // WHEN
        User foundUser = repository.findByEmail(email);

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUser.getUsername()).isEqualTo("username");
    }

    @Test
    void shouldFindUserById() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User expectedUser = new User(id, "username", "email@example.com", "password");
        when(jdbcTemplate.queryForObject(any(String.class), any(DataClassRowMapper.class), any(String.class)))
            .thenReturn(expectedUser);

        // WHEN
        User foundUser = repository.findById(id);

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(id);
        assertThat(foundUser.getUsername()).isEqualTo("username");
    }

    @Test
    void shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        when(jdbcTemplate.update(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class)))
            .thenReturn(1);

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        assertThat(insertedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(insertedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(insertedUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void shouldThrowConflictOnDuplicateKeyInsert() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        when(jdbcTemplate.update(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class)))
            .thenThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key violation"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
            .isInstanceOf(Conflict.class)
            .hasMessageContaining("Duplicate key violation");
    }
}