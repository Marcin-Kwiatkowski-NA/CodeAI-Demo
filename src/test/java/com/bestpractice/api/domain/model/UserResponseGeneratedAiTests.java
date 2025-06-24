package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserResponseGeneratedAiTests {

    @Test
    void getId_returnsCorrectId() {
        // GIVEN: Create a UserResponse object with an ID of "123".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN: Call the getId() method.
        String id = userResponse.getId();
        // THEN: Assert that the returned ID is "123".
        assertEquals("123", id);
    }

    @Test
    void getUsername_returnsCorrectUsername() {
        // GIVEN: Create a UserResponse object with a username of "john.doe".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN: Call the getUsername() method.
        String username = userResponse.getUsername();
        // THEN: Assert that the returned username is "john.doe".
        assertEquals("john.doe", username);
    }

    @Test
    void getEmail_returnsCorrectEmail() {
        // GIVEN: Create a UserResponse object with an email of "john.doe@example.com".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN: Call the getEmail() method.
        String email = userResponse.getEmail();
        // THEN: Assert that the returned email is "john.doe@example.com".
        assertEquals("john.doe@example.com", email);
    }
}
