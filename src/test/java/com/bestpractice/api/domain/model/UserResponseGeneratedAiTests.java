package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @Test
    void getId_returnsCorrectId() {
        assertEquals("123", userResponse.getId());
    }

    @Test
    void getUsername_returnsCorrectUsername() {
        assertEquals("john.doe", userResponse.getUsername());
    }

    @Test
    void getEmail_returnsCorrectEmail() {
        assertEquals("john.doe@example.com", userResponse.getEmail());
    }
}
