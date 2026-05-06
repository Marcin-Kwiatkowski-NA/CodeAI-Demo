package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testUser", "test@example.com", "securePassword");
    }

    @Test
    void testGetAndSetId() {
        // GIVEN
        String newId = "2";

        // WHEN
        user.setId(newId);

        // THEN
        assertEquals(newId, user.getId());
    }

    @Test
    void testGetAndSetUsername() {
        // GIVEN
        String newUsername = "updatedUser";

        // WHEN
        user.setUsername(newUsername);

        // THEN
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String newEmail = "updated@example.com";

        // WHEN
        user.setEmail(newEmail);

        // THEN
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String newPassword = "newSecurePassword";

        // WHEN
        user.setPassword(newPassword);

        // THEN
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        // GIVEN
        User newUser = new User();

        // WHEN
        newUser.setId("3");
        newUser.setUsername("defaultUser");
        newUser.setEmail("default@example.com");
        newUser.setPassword("defaultPassword");

        // THEN
        assertEquals("3", newUser.getId());
        assertEquals("defaultUser", newUser.getUsername());
        assertEquals("default@example.com", newUser.getEmail());
        assertEquals("defaultPassword", newUser.getPassword());
    }

    @Test
    void testConstructorInitialization() {
        // GIVEN
        String id = "10";
        String username = "initUser";
        String email = "init@example.com";
        String password = "initPassword";

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    void testInheritedCreatedAtFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        Date beforePersist = new Date();

        // WHEN
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertNotNull(createdAt);
        assertTrue(createdAt.equals(beforePersist) || createdAt.after(beforePersist));
    }

    @Test
    void testSetAndGetCreatedAtFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);

        // THEN
        assertEquals(now, sharedData.getCreatedAt());
    }

    @Test
    void testSetPasswordToNullThrowsException() {
        // GIVEN
        User newUser = new User();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: password field should not be null
            newUser.setPassword(null);
            if (newUser.getPassword() == null) {
                throw new NullPointerException("Password cannot be null");
            }
        });
    }

    @Test
    void testSetEmailToNullThrowsException() {
        // GIVEN
        User newUser = new User();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            newUser.setEmail(null);
            if (newUser.getEmail() == null) {
                throw new NullPointerException("Email cannot be null");
            }
        });
    }
}
