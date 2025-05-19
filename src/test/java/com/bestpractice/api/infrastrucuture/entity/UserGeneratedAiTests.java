package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UserGeneratedAiTests {

    @Test
    void constructor_should_initialize_user_with_correct_values() {
        // GIVEN: Create a new User object
        User user = new User("123", "testUser", "test@example.com", "password123");

        // WHEN: Access the object's attributes
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        // THEN: Verify that the attributes are set correctly
        assert id.equals("123") : "ID should be 123";
        assert username.equals("testUser") : "Username should be testUser";
        assert email.equals("test@example.com") : "Email should be test@example.com";
        assert password.equals("password123") : "Password should be password123";
    }

    @BeforeEach
    void setUp() {
        // Reset the createdAt attribute to a default value before each test
        User user = new User();
        user.setCreatedAt(new Date());
    }

    @Test
    void get_and_set_created_at_should_work_correctly() {
        // GIVEN: Create a new User object
        User user = new User();

        // WHEN: Set the createdAt attribute
        user.setCreatedAt(new Date());

        // THEN: Verify that the createdAt attribute is set correctly
        Date createdAt = user.getCreatedAt();
        assert createdAt != null : "CreatedAt should not be null";
    }
}
