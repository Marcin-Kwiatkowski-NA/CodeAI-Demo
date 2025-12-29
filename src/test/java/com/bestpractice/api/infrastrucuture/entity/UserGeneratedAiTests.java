package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void shouldCreateUserWithValidFields() {
        // GIVEN
        String id = "user-123";
        String username = "john_doe";
        String email = "john.doe@example.com";
        String password = "securePassword123";

        // WHEN
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // THEN
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        // WHEN & THEN
        assertThatThrownBy(() -> {
            user.setUsername(null);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("username");
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // WHEN & THEN
        assertThatThrownBy(() -> {
            user.setEmail(null);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("email");
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        // WHEN & THEN
        assertThatThrownBy(() -> {
            user.setPassword(null);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("password");
    }

    @Test
    void shouldSetCreatedAtOnPersist() {
        // WHEN
        user.onPrePersist();

        // THEN
        assertThat(user.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldNotAllowEmptyId() {
        // WHEN & THEN
        assertThatThrownBy(() -> {
            user.setId("");
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("id");
    }

    @Test
    void shouldHaveValidDefaultConstructor() {
        // THEN
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
        assertThat(user.getCreatedAt()).isNull();
    }
}
