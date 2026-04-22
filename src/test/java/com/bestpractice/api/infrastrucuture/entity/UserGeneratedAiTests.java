package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        User defaultUser = new User();

        // WHEN
        defaultUser.setId("10");
        defaultUser.setUsername("defaultUser");
        defaultUser.setEmail("default@example.com");
        defaultUser.setPassword("defaultPassword");

        // THEN
        assertEquals("10", defaultUser.getId());
        assertEquals("defaultUser", defaultUser.getUsername());
        assertEquals("default@example.com", defaultUser.getEmail());
        assertEquals("defaultPassword", defaultUser.getPassword());
    }

    @Test
    void testConstructorAssignsValuesCorrectly() {
        // GIVEN
        String id = "123";
        String username = "johnDoe";
        String email = "john@example.com";
        String password = "pass123";

        // WHEN
        User newUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, newUser.getId());
        assertEquals(username, newUser.getUsername());
        assertEquals(email, newUser.getEmail());
        assertEquals(password, newUser.getPassword());
    }

    @Test
    void testSetNullPasswordDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        user.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, user.getPassword());
    }

    @Test
    void testSetUsernameWithNullValueDoesNotThrowException() {
        // GIVEN
        String nullUsername = null;

        // WHEN
        user.setUsername(nullUsername);

        // THEN
        assertEquals(nullUsername, user.getUsername());
    }

    @Test
    void testSetEmailWithInvalidValueDoesNotThrowException() {
        // GIVEN
        String invalidEmail = "invalidEmail";

        // WHEN
        user.setEmail(invalidEmail);

        // THEN
        assertEquals(invalidEmail, user.getEmail());
    }

    @Test
    void testSetIdWithEmptyString() {
        // GIVEN
        String emptyId = "";

        // WHEN
        user.setId(emptyId);

        // THEN
        assertEquals(emptyId, user.getId());
    }

    @Test
    void testNullConstructorArguments() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;
        String password = null;

        // WHEN
        User nullUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, nullUser.getId());
        assertEquals(username, nullUser.getUsername());
        assertEquals(email, nullUser.getEmail());
        assertEquals(password, nullUser.getPassword());
    }

    @Test
    void testSetPasswordThrowsNoExceptionForEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        user.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, user.getPassword());
    }

    @Test
    void testSetEmailThrowsNoExceptionForNull() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        user.setEmail(nullEmail);

        // THEN
        assertEquals(nullEmail, user.getEmail());
    }

    @Test
    void testSetIdThrowsNoExceptionForNull() {
        // GIVEN
        String nullId = null;

        // WHEN
        user.setId(nullId);

        // THEN
        assertEquals(nullId, user.getId());
    }
}
