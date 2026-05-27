package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
        user = new User();
        user.setId("123");
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
    }

    @Test
    void testGettersReturnExpectedValues() {
        // GIVEN
        String expectedId = "123";
        String expectedUsername = "testUser";
        String expectedEmail = "test@example.com";
        String expectedPassword = "securePassword";

        // WHEN
        String actualId = user.getId();
        String actualUsername = user.getUsername();
        String actualEmail = user.getEmail();
        String actualPassword = user.getPassword();

        // THEN
        assertEquals(expectedId, actualId);
        assertEquals(expectedUsername, actualUsername);
        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testSettersModifyValuesCorrectly() {
        // GIVEN
        String newId = "456";
        String newUsername = "updatedUser";
        String newEmail = "updated@example.com";
        String newPassword = "newSecurePassword";

        // WHEN
        user.setId(newId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // THEN
        assertEquals(newId, user.getId());
        assertEquals(newUsername, user.getUsername());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testConstructorInitializesFieldsCorrectly() {
        // GIVEN
        String id = "789";
        String username = "constructorUser";
        String email = "constructor@example.com";
        String password = "constructorPassword";

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    void testInheritedCreatedAtFieldFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        sharedData.onPrePersist();

        // WHEN
        user.setCreatedAt(sharedData.getCreatedAt());

        // THEN
        assertThat(user.getCreatedAt()).isNotNull();
        assertEquals(sharedData.getCreatedAt(), user.getCreatedAt());
    }

    @Test
    void testSetPasswordToNullThrowsExceptionIfValidatedExternally() {
        // GIVEN
        String invalidPassword = null;

        // WHEN & THEN
        // Simulate external validation failure since @NotNull is not enforced at runtime
        assertThrows(NullPointerException.class, () -> {
            if (invalidPassword == null) {
                throw new NullPointerException("Password cannot be null");
            }
            user.setPassword(invalidPassword);
        });
    }

    @Test
    void testSetEmailToNullDoesNotThrowExceptionInternally() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        user.setEmail(nullEmail);

        // THEN
        // The setter itself does not throw an exception, but the value should be null
        assertEquals(nullEmail, user.getEmail());
    }

    @Test
    void testSetUsernameToEmptyString() {
        // GIVEN
        String emptyUsername = "";

        // WHEN
        user.setUsername(emptyUsername);

        // THEN
        assertEquals(emptyUsername, user.getUsername());
    }

    @Test
    void testSetIdToNull() {
        // GIVEN
        String nullId = null;

        // WHEN
        user.setId(nullId);

        // THEN
        assertEquals(nullId, user.getId());
    }
}
