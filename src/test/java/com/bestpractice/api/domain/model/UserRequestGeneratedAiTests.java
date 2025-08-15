package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void getUsername() {
        // GIVEN a UserRequest object
        userRequest.setUsername("testUser");
        // WHEN we call getUsername()
        String username = userRequest.getUsername();
        // THEN the returned username should be "testUser"
        assertEquals("testUser", username);
    }

    @Test
    void getEmail() {
        // GIVEN a UserRequest object
        userRequest.setEmail("test@example.com");
        // WHEN we call getEmail()
        String email = userRequest.getEmail();
        // THEN the returned email should be "test@example.com"
        assertEquals("test@example.com", email);
    }

    @Test
    void getPassword() {
        // GIVEN a UserRequest object
        userRequest.setPassword("secretPassword");
        // WHEN we call getPassword()
        String password = userRequest.getPassword();
        // THEN the returned password should be "secretPassword"
        assertEquals("secretPassword", password);
    }

    @Test
    void convert() {
        // GIVEN a UserRequest object with some data
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("secretPassword");
        // WHEN we call convert(id, encodePw)
        User user = userRequest.convert("123", "encoded");
        // THEN the converted User object should have the correct data
        assertEquals("123", user.getId());
        assertEquals("secretPassword", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testUser", user.getUsername());
    }
}
