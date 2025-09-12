package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

class UserResponseGeneratedAiTests {

    @Test
    void getId_returnsId() {
        // GIVEN a UserResponse object with id "123"
        UserResponse userResponse = new UserResponse("123", "testUser", "test@example.com");
        // WHEN we call get id()
        String id = userResponse.getId();
        // THEN the returned id should be "123"
        assertEquals("123", id);
    }

    @Test
    void getUsername_returnsUsername() {
        // GIVEN a UserResponse object with username "testUser"
        UserResponse userResponse = new UserResponse("123", "testUser", "test@example.com");
        // WHEN we call getUsername()
        String username = userResponse.getUsername();
        // THEN the returned username should be "testUser"
        assertEquals("testUser", username);
    }

    @Test
    void getEmail_returnsEmail() {
        // GIVEN a UserResponse object with email "test@example.com"
        UserResponse userResponse = new UserResponse("123", "testUser", "test@example.com");
        // WHEN we call getEmail()
        String email = userResponse.getEmail();
        // THEN the returned email should be "test@example.com"
        assertEquals("test@example.com", email);
    }
}
