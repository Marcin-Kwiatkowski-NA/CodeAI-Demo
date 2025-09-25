package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId("123");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
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
        String expectedPassword = "newPassword";

        // WHEN
        user.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    public void testConstructorWithParameters() {
        // GIVEN
        String id = "789";
        String username = "constructorUser";
        String email = "constructor@example.com";
        String password = "constructorPass";

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    public void testInheritedCreatedAtFromSharedData() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
        assertTrue(user.getCreatedAt().after(beforePersist) || user.getCreatedAt().equals(beforePersist));
    }

    @Test
    public void testSetAndGetCreatedAtFromSharedData() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        user.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, user.getCreatedAt());
    }
}
