package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

@Test
class UserGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        // This is a placeholder, you might need to reset the SharedData state as well
    }

    @Test
    void testUserConstructor() {
        // GIVEN: Create a new User object
        User user = new User("123", "testUser", "test@example.com", "password123");

        // WHEN: Access the user's properties
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        // THEN: Verify that the properties are set correctly
        assert id.equals("123") : "ID should be 123";
        assert username.equals("testUser") : "Username should be testUser";
        assert email.equals("test@example.com") : "Email should be test@example.com";
        assert password.equals("password123") : "Password should be password123";
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN: Create a new User object
        User user = new User("456", "anotherUser", "another@example.com", "anotherPassword");

        // WHEN: Set the user's properties
        user.setId("789");
        user.setUsername("newUser");
        user.setEmail("new@example.com");
        user.setPassword("newPassword");

        // THEN: Verify that the getters return the correct values
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        assert id.equals("789") : "ID should be 789";
        assert username.equals("newUser") : "Username should be newUser";
        assert email.equals("new@example.com") : "Email should be new@example.com";
        assert password.equals("newPassword") : "Password should be newPassword";
    }

    @Test
    void testCreatedAt() {
        // GIVEN: Create a new User object
        User user = new User("111", "testUser", "test@example.com", "password123");

        // WHEN: Access the createdAt property
        Date createdAt = user.getCreatedAt();

        // THEN: Verify that the createdAt property is set correctly
        assert createdAt.after(new Date(System.currentTimeMillis() - 3600000)) : "CreatedAt should be after one hour ago";
    }
}
