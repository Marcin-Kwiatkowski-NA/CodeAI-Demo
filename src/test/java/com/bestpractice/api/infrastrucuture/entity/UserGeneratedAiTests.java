package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId("123");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
        user.setCreatedAt(null);
    }

    @Test
    public void testGetAndSetId() {
        // GIVEN: a User object with a specific id
        String expectedId = "456";

        // WHEN: setting the id
        user.setId(expectedId);

        // THEN: the id should be updated correctly
        assertEquals(expectedId, user.getId());
    }

    @Test
    public void testGetAndSetUsername() {
        // GIVEN: a User object with a specific username
        String expectedUsername = "newuser";

        // WHEN: setting the username
        user.setUsername(expectedUsername);

        // THEN: the username should be updated correctly
        assertEquals(expectedUsername, user.getUsername());
    }

    @Test
    public void testGetAndSetEmail() {
        // GIVEN: a User object with a specific email
        String expectedEmail = "new@example.com";

        // WHEN: setting the email
        user.setEmail(expectedEmail);

        // THEN: the email should be updated correctly
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    public void testGetAndSetPassword() {
        // GIVEN: a User object with a specific password
        String expectedPassword = "newPassword";

        // WHEN: setting the password
        user.setPassword(expectedPassword);

        // THEN: the password should be updated correctly
        assertEquals(expectedPassword, user.getPassword());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a User object with no createdAt date
        assertNull(user.getCreatedAt());

        // WHEN: calling onPrePersist
        user.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(user.getCreatedAt());
        assertTrue(user.getCreatedAt() instanceof Date);
    }

    @Test
    public void testGetAndSetCreatedAt() {
        // GIVEN: a specific date
        Date expectedDate = new Date();

        // WHEN: setting createdAt
        user.setCreatedAt(expectedDate);

        // THEN: createdAt should be updated correctly
        assertEquals(expectedDate, user.getCreatedAt());
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        // GIVEN: values for constructor
        String id = "999";
        String username = "constructorUser";
        String email = "constructor@example.com";
        String password = "constructorPass";

        // WHEN: creating user via constructor
        User constructedUser = new User(id, username, email, password);

        // THEN: all fields should be initialized correctly
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    public void testSetIdToNull() {
        // GIVEN: a User object

        // WHEN: setting id to null
        user.setId(null);

        // THEN: id should be null
        assertNull(user.getId());
    }

    @Test
    public void testSetUsernameToNull() {
        // GIVEN: a User object

        // WHEN: setting username to null
        user.setUsername(null);

        // THEN: username should be null
        assertNull(user.getUsername());
    }

    @Test
    public void testSetEmailToNull() {
        // GIVEN: a User object

        // WHEN: setting email to null
        user.setEmail(null);

        // THEN: email should be null
        assertNull(user.getEmail());
    }

    @Test
    public void testSetPasswordToNull() {
        // GIVEN: a User object

        // WHEN: setting password to null
        user.setPassword(null);

        // THEN: password should be null
        assertNull(user.getPassword());
    }
}
