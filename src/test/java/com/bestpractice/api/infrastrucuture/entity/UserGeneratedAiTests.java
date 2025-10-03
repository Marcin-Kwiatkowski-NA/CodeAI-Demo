package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        // GIVEN: a fresh User instance before each test
        user = new User();
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN: a User instance and an id value
        String id = "12345";

        // WHEN: setting the id
        user.setId(id);

        // THEN: the id should be retrievable and match the set value
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN: a User instance and a username value
        String username = "testuser";

        // WHEN: setting the username
        user.setUsername(username);

        // THEN: the username should be retrievable and match the set value
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN: a User instance and an email value
        String email = "test@example.com";

        // WHEN: setting the email
        user.setEmail(email);

        // THEN: the email should be retrievable and match the set value
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN: a User instance and a password value
        String password = "securePassword123";

        // WHEN: setting the password
        user.setPassword(password);

        // THEN: the password should be retrievable and match the set value
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testConstructorWithParameters() {
        // GIVEN: parameter values for the User constructor
        String id = "1";
        String username = "user1";
        String email = "user1@example.com";
        String password = "pass123";

        // WHEN: creating a User with the parameterized constructor
        User constructedUser = new User(id, username, email, password);

        // THEN: all fields should match the provided values
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    public void testInheritedSetAndGetCreatedAt() {
        // GIVEN: a User instance and a Date value
        Date now = new Date();

        // WHEN: setting the createdAt date
        user.setCreatedAt(now);

        // THEN: the createdAt date should be retrievable and match the set value
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    public void testInheritedOnPrePersistSetsCreatedAt() {
        // GIVEN: a User instance with no createdAt date set
        assertNull(user.getCreatedAt());

        // WHEN: calling onPrePersist
        user.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(user.getCreatedAt());
    }

    @Test
    public void testSetFieldsToNull() {
        // GIVEN: a User instance

        // WHEN: setting all fields to null
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);

        // THEN: all fields should be null
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    public void testMultipleFieldUpdates() {
        // GIVEN: a User instance with initial values
        user.setId("initId");
        user.setUsername("initUser");
        user.setEmail("init@example.com");
        user.setPassword("initPass");

        // WHEN: updating multiple fields
        String newId = "newId";
        String newUsername = "newUser";
        String newEmail = "new@example.com";
        String newPassword = "newPass";
        user.setId(newId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // THEN: all updated fields should match the new values
        assertEquals(newId, user.getId());
        assertEquals(newUsername, user.getUsername());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPassword, user.getPassword());
    }
}
