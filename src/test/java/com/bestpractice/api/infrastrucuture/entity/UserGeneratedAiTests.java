package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.SharedData;
import org.junit.jupiter.api.BeforeEach;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        // Initialize a new User object before each test
        user = new User();
    }

    @org.junit.jupiter.api.Test
    void constructor_should_initialize_fields() {
        // GIVEN: A new User object is created
        // WHEN: The constructor is called with arguments
        // THEN: The user's fields (id, username, email, password) are initialized with the provided values
        User newUser = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", newUser.getId());
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @org.junit.jupiter.api.Test
    void setId_should_set_the_id() {
        // GIVEN: A User object is created
        // WHEN: The setId method is called with a new id value
        // THEN: The user's id field is updated with the new value
        user.setId("456");
        assertEquals("456", user.getId());
    }

    @org.junit.jupiter.api.Test
    void setUsername_should_set_the_username() {
        // GIVEN: A User object is created
        // WHEN: The setUsername method is called with a new username value
        // THEN: The user's username field is updated with the new value
        user.setUsername("anotherUser");
        assertEquals("anotherUser", user.getUsername());
    }

    @org.junit.jupiter.api.Test
    void setEmail_should_set_the_email() {
        // GIVEN: A User object is created
        // WHEN: The setEmail method is called with a new email value
        // THEN: The user's email field is updated with the new value
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @org.junit.jupiter.api.Test
    void setPassword_should_set_the_password() {
        // GIVEN: A User object is created
        // WHEN: The setPassword method is called with a new password value
        // THEN: The user's password field is updated with the new value
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @org.junit.jupiter.api.Test
    void createdAt_should_return_the_creation_date() {
        // GIVEN: A User object is created
        // WHEN: The getCreatedAt method is called
        // THEN: The user's createdAt field (which should be initialized by the constructor) is returned
        Date createdAt = user.getCreatedAt();
        assertNotNull(createdAt);
    }
}
