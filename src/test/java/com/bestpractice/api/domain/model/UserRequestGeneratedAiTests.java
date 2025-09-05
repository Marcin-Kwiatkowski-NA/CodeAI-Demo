package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.extension.ExtendWith.withExtension;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testConvert() {
        // GIVEN a UserRequest object with some data
        String id = "123";
        String encodePw = "password";
        userRequest.setUsername("john.doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("encoded_password");

        // WHEN the convert method is called
        User user = userRequest.convert(id, encodePw);

        // THEN the resulting User object should have the correct data
        assertEquals("123", user.getId());
        assertEquals("encoded_password", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("john.doe", user.getUsername());
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN a UserRequest object
        userRequest.setUsername("jane.doe");
        userRequest.setEmail("jane.doe@example.com");
        userRequest.setPassword("another_password");

        // WHEN the setters are called
        userRequest.setUsername("new_username");
        userRequest.setEmail("new_email");
        userRequest.setPassword("new_password");

        // THEN the getters should return the updated values
        assertEquals("new_username", userRequest.getUsername());
        assertEquals("new_email", userRequest.getEmail());
        assertEquals("new_password", userRequest.getPassword());
    }
}
