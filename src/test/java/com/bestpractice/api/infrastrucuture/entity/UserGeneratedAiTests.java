package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        // GIVEN - a fresh User instance before each test
        user = new User();
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);
        user.setCreatedAt(null);
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        user.setId(expectedId);

        // THEN
        assertEquals(expectedId, user.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        user.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, user.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        user.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // Security-sensitive

        // WHEN
        user.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    public void testConstructorWithParameters() {
        // GIVEN
        String id = "1";
        String username = "user1";
        String email = "user1@example.com";
        String password = "pass123"; // Security-sensitive

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    public void testSetAndGetCreatedAtFromSharedData() {
        // GIVEN
        Date now = new Date();

        // WHEN
        user.setCreatedAt(now);

        // THEN
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        assertNull(user.getCreatedAt());

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
    }

    @Test
    public void testSetPasswordWithNullDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        user.setPassword(nullPassword);

        // THEN
        assertNull(user.getPassword());
    }

    @Test
    public void testSetEmailWithNullDoesNotThrowException() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        user.setEmail(nullEmail);

        // THEN
        assertNull(user.getEmail());
    }

    @Test
    public void testSetUsernameWithNullDoesNotThrowException() {
        // GIVEN
        String nullUsername = null;

        // WHEN
        user.setUsername(nullUsername);

        // THEN
        assertNull(user.getUsername());
    }

    @Test
    public void testSetIdWithNullDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN
        user.setId(nullId);

        // THEN
        assertNull(user.getId());
    }
}
