package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class UserGeneratedAiTests {

    private User user;

    @Test
    void constructor_should_initialize_user_with_provided_values() {
        user = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void getId_should_return_the_user_id() {
        user.setId("456");
        assertEquals("456", user.getId());
    }

    @Test
    void setUsername_should_set_the_username() {
        user.setUsername("anotherUser");
        assertEquals("anotherUser", user.getUsername());
    }

    @Test
    void setEmail_should_set_the_email() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void setPassword_should_set_the_password() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void createdAt_should_return_the_current_date() {
        assertEquals(new Date(), user.getCreatedAt());
    }
}
