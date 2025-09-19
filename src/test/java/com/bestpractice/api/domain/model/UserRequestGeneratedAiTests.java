package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
        String encodePw = "encodedPassword";
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        // WHEN the convert method is called
        User user = userRequest.convert(id, encodePw);

        // THEN the resulting User object should have the correct data
        assertEquals("123", user.getId());
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN a UserRequest object
        userRequest.setUsername("newUsername");
        userRequest.setEmail("newEmail@example.com");
        userRequest.setPassword("newPassword");

        // WHEN the getters are called
        String username = userRequest.getUsername();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        // THEN the getters should return the correct values
        assertEquals("newUsername", username);
        assertEquals("newEmail@example.com", email);
        assertEquals("newPassword", password);
    }
}
