package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserResponseGeneratedAiTests {

    @Test
    void getId_returnsCorrectId() {
        // GIVEN: A UserResponse object is created with an ID of "123".
        // WHEN: The getId() method is called.
        // THEN: The method returns the correct ID, "123".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        assertEquals("123", userResponse.getId());
    }

    @Test
    void getUsername_returnsCorrectUsername() {
        // GIVEN: A UserResponse object is created with a username of "john.doe".
        // WHEN: The getUsername() method is called.
        // THEN: The method returns the correct username, "john.doe".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        assertEquals("john.doe", userResponse.getUsername());
    }

    @Test
    void getEmail_returnsCorrectEmail() {
        // GIVEN: A UserResponse object is created with an email of "john.doe@example.com".
        // WHEN: The getEmail() method is called.
        // THEN: The method returns the correct email, "john.doe@example.com".
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        assertEquals("john.doe@example.com", userResponse.getEmail());
    }
}
