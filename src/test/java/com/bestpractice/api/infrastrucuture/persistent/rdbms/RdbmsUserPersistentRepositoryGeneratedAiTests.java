package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    private User user;
    private String userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID().toString();
        user = new User(userId, "testUser", "test@example.com", "password");
    }

    @Test
    void newId_ShouldGenerateUUID() {
        // GIVEN
        // WHEN
        String newId = repository.newId();
        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).isNotEmpty();
    }

    @Test
    void findByEmail_ShouldReturnUser() {
        // GIVEN
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), eq("test@example.com")))
                .thenReturn(user);
        // WHEN
        User foundUser = repository.findByEmail("test@example.com");
        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void findById_ShouldReturnUser() {
        // GIVEN
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), eq(userId)))
                .thenReturn(user);
        // WHEN
        User foundUser = repository.findById(userId);
        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(userId);
    }

    @Test
    void insert_ShouldInsertUser() {
        // GIVEN
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);
        // WHEN
        User insertedUser = repository.insert(user);
        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo(userId);
    }

    @Test
    void replace_ShouldUpdateUser() {
        // GIVEN
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);
        // WHEN
        User updatedUser = repository.replace(userId, user);
        // THEN
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(userId);
    }

    @Test
    void removeById_ShouldDeleteUser() {
        // GIVEN
        when(jdbcTemplate.update(any(String.class), eq(userId))).thenReturn(1);
        // WHEN
        boolean result = repository.removeById(userId);
        // THEN
        assertThat(result).isTrue();
    }
}
