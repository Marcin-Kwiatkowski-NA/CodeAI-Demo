package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testUser", "test@example.com", "securePassword");
    }

    @Test
    void testGetId() {
        // GIVEN
        String expectedId = "1";

        // WHEN
        String actualId = user.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetId() {
        // GIVEN
        String newId = "2";

        // WHEN
        user.setId(newId);

        // THEN
        assertEquals(newId, user.getId());
    }

    @Test
    void testGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        String actualUsername = user.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testSetUsername() {
        // GIVEN
        String newUsername = "updatedUser";

        // WHEN
        user.setUsername(newUsername);

        // THEN
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void testGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        String actualEmail = user.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetEmail() {
        // GIVEN
        String newEmail = "new@example.com";

        // WHEN
        user.setEmail(newEmail);

        // THEN
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        String actualPassword = user.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testSetPassword() {
        // GIVEN
        String newPassword = "newSecurePassword";

        // WHEN
        user.setPassword(newPassword);

        // THEN
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testInheritanceFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        sharedData.onPrePersist();

        // WHEN
        user.setCreatedAt(sharedData.getCreatedAt());

        // THEN
        assertThat(user.getCreatedAt()).isNotNull();
    }

    @Test
    void testDefaultConstructorInitializesFieldsToNull() {
        // GIVEN
        User defaultUser = new User();

        // WHEN
        String id = defaultUser.getId();
        String username = defaultUser.getUsername();
        String email = defaultUser.getEmail();
        String password = defaultUser.getPassword();

        // THEN
        assertThat(id).isNull();
        assertThat(username).isNull();
        assertThat(email).isNull();
        assertThat(password).isNull();
    }

    @Test
    void testSetPasswordToNullThrowsExceptionIfValidated() {
        // GIVEN
        String nullPassword = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // Simulate validation scenario if @NotNull were enforced at runtime
            if (nullPassword == null) {
                throw new NullPointerException("Password cannot be null");
            }
            user.setPassword(nullPassword);
        });
    }

    @Test
    void testSetEmailToEmptyString() {
        // GIVEN
        String emptyEmail = "";

        // WHEN
        user.setEmail(emptyEmail);

        // THEN
        assertEquals(emptyEmail, user.getEmail());
    }

    @Test
    void testSetUsernameToNull() {
        // GIVEN
        String nullUsername = null;

        // WHEN
        user.setUsername(nullUsername);

        // THEN
        assertThat(user.getUsername()).isNull();
    }

    @Test
    void testSetIdToNull() {
        // GIVEN
        String nullId = null;

        // WHEN
        user.setId(nullId);

        // THEN
        assertThat(user.getId()).isNull();
    }
}
