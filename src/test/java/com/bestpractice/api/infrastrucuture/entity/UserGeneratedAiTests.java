package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserGeneratedAiTests {

    @Test
    public void constructor_should_initialize_user_with_correct_values() {
        // GIVEN: Create a new User object
        User user = new User("123", "testuser", "test@example.com", "password123");

        // WHEN: Access the user's attributes
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        // THEN: Verify that the attributes are set correctly
        Assertions.assertEquals("123", id);
        Assertions.assertEquals("testuser", username);
        Assertions.assertEquals("test@example.com", email);
        Assertions.assertEquals("password123", password);
    }

    @Test
    public void setId_should_set_user_id() {
        // GIVEN: Create a new User object
        User user = new User("", "testuser", "test@example.com", "password123");

        // WHEN: Set the user's id
        user.setId("456");

        // THEN: Verify that the user's id is set correctly
        Assertions.assertEquals("456", user.getId());
    }

    @Test
    public void setUsername_should_set_user_username() {
        // GIVEN: Create a new User object
        User user = new User("123", "", "test@example.com", "password123");

        // WHEN: Set the user's username
        user.setUsername("newuser");

        // THEN: Verify that the user's username is set correctly
        Assertions.assertEquals("newuser", user.getUsername());
    }

    @Test
    public void setEmail_should_set_user_email() {
        // GIVEN: Create a new User object
        User user = new User("123", "testuser", "", "password123");

        // WHEN: Set the user's email
        user.setEmail("newemail@example.com");

        // THEN: Verify that the user's email is set correctly
        Assertions.assertEquals("newemail@example.com", user.getEmail());
    }

    @Test
    public void setPassword_should_set_user_password() {
        // GIVEN: Create a new User object
        User user = new User("123", "testuser", "test@example.com", "");

        // WHEN: Set the user's password
        user.setPassword("newpassword");

        // THEN: Verify that the user's password is set correctly
        Assertions.assertEquals("newpassword", user.getPassword());
    }
}
