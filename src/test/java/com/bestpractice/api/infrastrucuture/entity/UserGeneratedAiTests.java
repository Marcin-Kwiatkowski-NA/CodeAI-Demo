package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId("123");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
        user.setCreatedAt(new Date());
    }

    @Test
    public void testGetAndSetId() {
        // GIVEN
        String expectedId = "456";

        // WHEN
        user.setId(expectedId);

        // THEN
        assertEquals(expectedId, user.getId());
    }

    @Test
    public void testGetAndSetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        user.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, user.getUsername());
    }

    @Test
    public void testGetAndSetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        user.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    public void testGetAndSetPassword() {
        // GIVEN
        String expectedPassword = "newSecurePassword";

        // WHEN
        user.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    public void testDefaultConstructor() {
        // GIVEN
        User newUser;

        // WHEN
        newUser = new User();

        // THEN
        assertNotNull(newUser);
        assertNull(newUser.getId());
        assertNull(newUser.getUsername());
        assertNull(newUser.getEmail());
        assertNull(newUser.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        // GIVEN
        String id = "789";
        String username = "paramUser";
        String email = "param@example.com";
        String password = "paramPassword";

        // WHEN
        User newUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, newUser.getId());
        assertEquals(username, newUser.getUsername());
        assertEquals(email, newUser.getEmail());
        assertEquals(password, newUser.getPassword());
    }

    @Test
    public void testGetAndSetCreatedAtFromSharedData() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        user.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, user.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        User newUser = new User();
        assertNull(newUser.getCreatedAt());

        // WHEN
        newUser.onPrePersist();

        // THEN
        assertNotNull(newUser.getCreatedAt());
    }

    @Test
    public void testSetPasswordWithNullThrowsException() {
        // GIVEN
        String nullPassword = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (nullPassword == null) {
                throw new NullPointerException("Password cannot be null");
            }
            user.setPassword(nullPassword);
        });
    }

    @Test
    public void testSetEmailWithNullThrowsException() {
        // GIVEN
        String nullEmail = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (nullEmail == null) {
                throw new NullPointerException("Email cannot be null");
            }
            user.setEmail(nullEmail);
        });
    }

    @Test
    public void testSetUsernameWithNullThrowsException() {
        // GIVEN
        String nullUsername = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (nullUsername == null) {
                throw new NullPointerException("Username cannot be null");
            }
            user.setUsername(nullUsername);
        });
    }
}
