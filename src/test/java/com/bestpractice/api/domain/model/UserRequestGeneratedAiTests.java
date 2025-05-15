package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testSetUsername() {
        // GIVEN: A new UserRequest object is created.
        // WHEN: The username is set to "testUser".
        userRequest.setUsername("testUser");
        // THEN: The username property is set to "testUser".
        assertEquals("testUser", userRequest.getUsername());
    }

    @Test
    void testSetEmail() {
        // GIVEN: A new UserRequest object is created.
        // WHEN: The email is set to "test@example.com".
        userRequest.setEmail("test@example.com");
        // THEN: The email property is set to "test@example.com".
        assertEquals("test@example.com", userRequest.getEmail());
    }

    @Test
    void testSetPassword() {
        // GIVEN: A new UserRequest object is created.
        // WHEN: The password is set to "secretPassword".
        userRequest.setPassword("secretPassword");
        // THEN: The password property is set to "secretPassword".
        assertEquals("secretPassword", userRequest.getPassword());
    }

    @Test
    void testConvertUser() {
        // GIVEN: A UserRequest object with username "testUser", email "test@example.com", and password "secretPassword".
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("secretPassword");

        // WHEN: The convert method is called with id "123" and encoded password "encodedPw".
        User user = userRequest.convert("123", "encodedPw");

        // THEN: A User object is created with id "123", username "testUser", email "test@example.com", and password "encodedPw".
        assertEquals("123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("encodedPw", user.getPassword());
    }
}
