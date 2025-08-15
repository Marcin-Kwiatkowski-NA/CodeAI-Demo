package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

@ExtendWith(MyExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void constructor_should_initialize_user_with_correct_values() {
        // GIVEN: A new User object is created.
        // WHEN: The constructor is called with specific values.
        // THEN: The user's id, username, email, and password fields are initialized with the provided values.
        User newUser = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", newUser.getId());
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @Test
    void getId_should_return_the_user_id() {
        // GIVEN: A User object is created.
        // WHEN: The getId() method is called.
        // THEN: The user's id is returned.
        user.setId("456");
        assertEquals("456", user.getId());
    }

    @Test
    void setUsername_should_set_the_username() {
        // GIVEN: A User object is created.
        // WHEN: The setUsername() method is called with a new username.
        // THEN: The user's username is updated to the new value.
        user.setUsername("anotherUser");
        assertEquals("anotherUser", user.getUsername());
    }

    @Test
    void setEmail_should_set_the_email() {
        // GIVEN: A User object is created.
        // WHEN: The setEmail() method is called with a new email address.
        // THEN: The user's email is updated to the new value.
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void setPassword_should_set_the_password() {
        // GIVEN: A User object is created.
        // WHEN: The setPassword() method is called with a new password.
        // THEN: The user's password is updated to the new value.
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void createdAt_should_return_the_creation_date() {
        // GIVEN: A User object is created.
        // WHEN: The getCreatedAt() method is called.
        // THEN: The creation date (current date and time) is returned.
        Date createdAt = user.getCreatedAt();
        assertEquals(new Date(), createdAt);
    }

    @Test
    void onPrePersist_should_set_creation_date_to_current_time() {
        // GIVEN: A User object is created.
        // WHEN: The onPrePersist() method is called.
        // THEN: The creation date is set to the current date and time.
        Date createdAt = user.getCreatedAt();
        Date expectedCreatedAt = new Date();
        assertEquals(expectedCreatedAt, createdAt);
    }
}
