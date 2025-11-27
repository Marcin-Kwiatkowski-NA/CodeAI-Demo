package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void givenNewUser_whenSetId_thenIdIsSetCorrectly() {
        // GIVEN
        String id = "12345";

        // WHEN
        user.setId(id);

        // THEN
        assertEquals("12345", user.getId());
    }

    @Test
    void givenNewUser_whenSetUsername_thenUsernameIsSetCorrectly() {
        // GIVEN
        String username = "testUser";

        // WHEN
        user.setUsername(username);

        // THEN
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void givenNewUser_whenSetEmail_thenEmailIsSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        user.setEmail(email);

        // THEN
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void givenNewUser_whenSetPassword_thenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        user.setPassword(password);

        // THEN
        assertEquals("securePassword", user.getPassword());
    }

    @Test
    void givenNewUser_whenConstructedWithParameters_thenFieldsAreSetCorrectly() {
        // GIVEN
        String id = "12345";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        User userWithParams = new User(id, username, email, password);

        // THEN
        assertEquals("12345", userWithParams.getId());
        assertEquals("testUser", userWithParams.getUsername());
        assertEquals("test@example.com", userWithParams.getEmail());
        assertEquals("securePassword", userWithParams.getPassword());
    }

    @Test
    void givenNewUser_whenGetCreatedAt_thenCreatedAtIsNotNull() {
        // GIVEN
        SharedData sharedData = new User();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertEquals(Date.class, sharedData.getCreatedAt().getClass());
    }

    @Test
    void givenNullId_whenSetId_thenIdIsSetToNull() {
        // GIVEN
        String id = null;

        // WHEN
        user.setId(id);

        // THEN
        assertEquals(null, user.getId());
    }

    @Test
    void givenNullUsername_whenSetUsername_thenUsernameIsSetToNull() {
        // GIVEN
        String username = null;

        // WHEN
        user.setUsername(username);

        // THEN
        assertEquals(null, user.getUsername());
    }

    @Test
    void givenNullEmail_whenSetEmail_thenEmailIsSetToNull() {
        // GIVEN
        String email = null;

        // WHEN
        user.setEmail(email);

        // THEN
        assertEquals(null, user.getEmail());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenPasswordIsSetToNull() {
        // GIVEN
        String password = null;

        // WHEN
        user.setPassword(password);

        // THEN
        assertEquals(null, user.getPassword());
    }

    @Test
    void givenNewUser_whenSetFields_thenAllFieldsAreSetCorrectly() {
        // GIVEN
        String id = "67890";
        String username = "newUser";
        String email = "new@example.com";
        String password = "newPassword";

        // WHEN
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // THEN
        assertEquals("67890", user.getId());
        assertEquals("newUser", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void givenNewUser_whenSetNullFields_thenFieldsAreSetToNull() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;
        String password = null;

        // WHEN
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // THEN
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
    }
}
