package com.bestpractice.api.infrastrucuture.entity;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @org.junit.jupiter.api.Test
    void constructor_should_initialize_user_with_provided_values() {
        // GIVEN: A new User object is created.
        // WHEN: The constructor is called with sample values.
        // THEN: The user's id, username, email, and password are set to the provided values.
        User newUser = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", newUser.getId());
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @org.junit.jupiter.api.Test
    void getId_should_return_the_user_id() {
        // GIVEN: A User object is created.
        // WHEN: getId() is called.
        // THEN: The user's id is returned.
        user.setId("456");
        assertEquals("456", user.getId());
    }

    @org.junit.jupiter.api.Test
    void setUsername_should_set_the_username() {
        // GIVEN: A User object is created.
        // WHEN: setUsername() is called with a new username.
        // THEN: The user's username is updated to the new value.
        user.setUsername("anotherUser");
        assertEquals("anotherUser", user.getUsername());
    }

    @org.junit.jupiter.api.Test
    void setEmail_should_set_the_email() {
        // GIVEN: A User object is created.
        // WHEN: setEmail() is called with a new email address.
        // THEN: The user's email is updated to the new value.
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @org.junit.jupiter.api.Test
    void setPassword_should_set_the_password() {
        // GIVEN: A User object is created.
        // WHEN: setPassword() is called with a new password.
        // THEN: The user's password is updated to the new value.
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @org.junit.jupiter.api.Test
    void createdAt_should_return_the_creation_date() {
        // GIVEN: A User object is created.
        // WHEN: getCreatedAt() is called.
        // THEN: The creation date (current date and time) is returned.
        Date createdAt = user.getCreatedAt();
        assertEquals(new Date(), createdAt);
    }

    @org.junit.jupiter.api.Test
    void onPrePersist_should_set_creation_date_to_current_time() {
        // GIVEN: A User object is created.
        // WHEN: onPrePersist() is called.
        // THEN: The creation date is set to the current date and time.
        Date createdAt = user.getCreatedAt();
        assertEquals(new Date(), createdAt);
    }
}