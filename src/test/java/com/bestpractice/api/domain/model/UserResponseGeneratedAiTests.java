package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        userResponse = new UserResponse("1", "testUser", "test@example.com");
    }

    @Test
    public void shouldReturnCorrectId() {
        assertEquals("1", userResponse.getId());
    }

    @Test
    public void shouldReturnCorrectUsername() {
        assertEquals("testUser", userResponse.getUsername());
    }

    @Test
    public void shouldReturnCorrectEmail() {
        assertEquals("test@example.com", userResponse.getEmail());
    }
}
