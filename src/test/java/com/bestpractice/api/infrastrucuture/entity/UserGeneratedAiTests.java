package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserGeneratedAiTests {

    private User user;

    @Test
    void constructor_should_initialize_user_with_correct_values() {
        // GIVEN: A new User object is created.
        // WHEN: The constructor is called with specific values.
        // THEN: The user's id, username, email, and password should be initialized correctly.
        User newUser = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", newUser.getId());
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @Test
    void getId_should_return_the_user_id() {
        // GIVEN: A User object is created.
        // WHEN: getId() method is called.
        // THEN: The user's id should be returned.
        user.setId("456");
        assertEquals("456", user.getId());
    }

    @Test
    void getUsername_should_return_the_user_username() {
        // GIVEN: A User object is created.
        // WHEN: getUsername() method is called.
        // THEN: The user's username should be returned.
        user.setUsername("anotherUser");
        assertEquals("anotherUser", user.getUsername());
    }

    @Test
    void getEmail_should_return_the_user_email() {
        // GIVEN: A User object is created.
        // WHEN: getEmail() method is called.
        // THEN: The user's email should be returned.
        user.setEmail("another@example.com");
        assertEquals("another@example.com", user.getEmail());
    }

    @Test
    void getPassword_should_return_the_user_password() {
        // GIVEN: A User object is created.
        // WHEN: getPassword() method is called.
        // THEN: The user's password should be returned.
        user.setPassword("securePassword");
        assertEquals("securePassword", user.getPassword());
    }

    @Test
    void onPrePersist_should_set_created_at_to_current_date() {
        // GIVEN: A User object is created.
        // WHEN: onPrePersist() method is called.
        // THEN: The createdAt field should be set to the current date and time.
        user.setId("789");
        user.setUsername("testUser2");
        user.setEmail("test@example.org");
        user.setPassword("anotherPassword");

        assertEquals(new Date(), user.getCreatedAt());
    }
}
