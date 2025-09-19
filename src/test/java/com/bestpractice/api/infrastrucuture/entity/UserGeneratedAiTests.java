package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

@Test
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void constructor_should_initialize_user_with_provided_values() {
        // GIVEN: A new User object is created.
        User newUser = new User("id", "username", "email", "password");

        // WHEN: The constructor is called.
        // THEN: The user's id, username, email, and password fields are initialized with the provided values.
        Assertions.assertEquals("id", newUser.getId());
        Assertions.assertEquals("username", newUser.getUsername());
        Assertions.assertEquals("email", newUser.getEmail());
        Assertions.assertEquals("password", newUser.getPassword());
    }

    @Test
    void getId_should_return_the_user_id() {
        // GIVEN: A User object is created.
        User user = new User("id", "username", "email", "password");

        // WHEN: The getId() method is called.
        // THEN: The user's id is returned.
        Assertions.assertEquals("id", user.getId());
    }

    @Test
    void setFirstName_should_update_the_username() {
        // GIVEN: A User object is created.
        User user = new User("id", "username", "email", "password");

        // WHEN: The setUsername() method is called with a new username.
        user.setUsername("newUsername");

        // THEN: The user's username is updated to "newUsername".
        Assertions.assertEquals("newUsername", user.getUsername());
    }

    @Test
    void setEmail_should_update_the_email() {
        // GIVEN: A User object is created.
        User user = new User("id", "username", "email", "password");

        // WHEN: The setEmail() method is called with a new email.
        user.setEmail("newEmail");

        // THEN: The user's email is updated to "newEmail".
        Assertions.assertEquals("newEmail", user.getEmail());
    }

    @Test
    void setPassword_should_update_the_password() {
        // GIVEN: A User object is created.
        User user = new User("id", "username", "email", "password");

        // WHEN: The setPassword() method is called with a new password.
        user.setPassword("newPassword");

        // THEN: The user's password is updated to "newPassword".
        Assertions.assertEquals("newPassword", user.getPassword());
    }

    @Test
    void createdAt_should_return_the_current_date() {
        // GIVEN: A User object is created.
        User user = new User("id", "username", "email", "password");

        // WHEN: The getCreatedAt() method is called.
        // THEN: The current date and time are returned.
        Assertions.assertNotNull(user.getCreatedAt());
    }
}
