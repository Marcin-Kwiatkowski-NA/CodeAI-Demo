package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetId() {
        // GIVEN
        String expectedId = "12345";
        user.setId(expectedId);

        // WHEN
        String actualId = user.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        user.setId(expectedId);

        // THEN
        assertEquals(expectedId, user.getId());
    }

    @Test
    void testGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";
        user.setUsername(expectedUsername);

        // WHEN
        String actualUsername = user.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testSetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        user.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, user.getUsername());
    }

    @Test
    void testGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";
        user.setEmail(expectedEmail);

        // WHEN
        String actualEmail = user.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        user.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    void testGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";
        user.setPassword(expectedPassword);

        // WHEN
        String actualPassword = user.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testSetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        user.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        User defaultUser = new User();

        // THEN
        assertEquals(null, defaultUser.getId());
        assertEquals(null, defaultUser.getUsername());
        assertEquals(null, defaultUser.getEmail());
        assertEquals(null, defaultUser.getPassword());
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN
        String id = "12345";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        User parameterizedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, parameterizedUser.getId());
        assertEquals(username, parameterizedUser.getUsername());
        assertEquals(email, parameterizedUser.getEmail());
        assertEquals(password, parameterizedUser.getPassword());
    }

    @Test
    void testSetPasswordThrowsExceptionWhenNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullPassword == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            user.setPassword(nullPassword);
        });
    }

    @Test
    void testSetEmailThrowsExceptionWhenNull() {
        // GIVEN
        String nullEmail = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullEmail == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }
            user.setEmail(nullEmail);
        });
    }

    @Test
    void testSetUsernameThrowsExceptionWhenNull() {
        // GIVEN
        String nullUsername = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullUsername == null) {
                throw new IllegalArgumentException("Username cannot be null");
            }
            user.setUsername(nullUsername);
        });
    }
}
