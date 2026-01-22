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

class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: a UserResponse instance with known values
        userResponse = new UserResponse("123", "john_doe", "john@example.com");
    }

    @Test
    void testGetId() {
        // GIVEN: userResponse initialized in setUp()
        // WHEN: retrieving the id
        String id = userResponse.getId();
        // THEN: the id should match the value provided in the constructor
        assertEquals("123", id);
    }

    @Test
    void testGetUsername() {
        // GIVEN: userResponse initialized in setUp()
        // WHEN: retrieving the username
        String username = userResponse.getUsername();
        // THEN: the username should match the value provided in the constructor
        assertEquals("john_doe", username);
    }

    @Test
    void testGetEmail() {
        // GIVEN: userResponse initialized in setUp()
        // WHEN: retrieving the email
        String email = userResponse.getEmail();
        // THEN: the email should match the value provided in the constructor
        assertEquals("john@example.com", email);
    }

    @Test
    void testConstructorWithNullValues() {
        // GIVEN: null values for all fields
        // WHEN: creating a UserResponse with nulls
        UserResponse nullUser = new UserResponse(null, null, null);
        // THEN: getters should return null
        assertEquals(null, nullUser.getId());
        assertEquals(null, nullUser.getUsername());
        assertEquals(null, nullUser.getEmail());
    }
}
