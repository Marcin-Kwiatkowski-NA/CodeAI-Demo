package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void givenValidId_whenSetId_thenIdIsSetCorrectly() {
        // GIVEN
        String id = "12345";

        // WHEN
        user.setId(id);

        // THEN
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void givenValidUsername_whenSetUsername_thenUsernameIsSetCorrectly() {
        // GIVEN
        String username = "testUser";

        // WHEN
        user.setUsername(username);

        // THEN
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    void givenValidEmail_whenSetEmail_thenEmailIsSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        user.setEmail(email);

        // THEN
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void givenValidPassword_whenSetPassword_thenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        user.setPassword(password);

        // THEN
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void givenValidConstructorArguments_whenCreateUser_thenFieldsAreSetCorrectly() {
        // GIVEN
        String id = "12345";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        User user = new User(id, username, email, password);

        // THEN
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
