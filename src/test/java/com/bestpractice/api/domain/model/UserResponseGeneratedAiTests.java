package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // GIVEN a UserResponse instance with known values
        userResponse = new UserResponse("123", "john_doe", "john@example.com");
    }

    @Test
    void testGetIdReturnsCorrectValue() {
        // GIVEN
        // userResponse set up in @BeforeEach
        // WHEN
        String id = userResponse.getId();
        // THEN
        assertThat(id).isEqualTo("123");
    }

    @Test
    void testGetUsernameReturnsCorrectValue() {
        // GIVEN
        // WHEN
        String username = userResponse.getUsername();
        // THEN
        assertThat(username).isEqualTo("john_doe");
    }

    @Test
    void testGetEmailReturnsCorrectValue() {
        // GIVEN
        // WHEN
        String email = userResponse.getEmail();
        // THEN
        assertThat(email).isEqualTo("john@example.com");
    }

    @Test
    void testConstructorAssignsValues() {
        // GIVEN
        UserResponse ur = new UserResponse("456", "jane_doe", "jane@example.com");
        // WHEN
        // THEN
        assertThat(ur.getId()).isEqualTo("456");
        assertThat(ur.getUsername()).isEqualTo("jane_doe");
        assertThat(ur.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void testGettersWithNullValues() {
        // GIVEN
        UserResponse ur = new UserResponse(null, null, null);
        // WHEN
        // THEN
        assertThat(ur.getId()).isNull();
        assertThat(ur.getUsername()).isNull();
        assertThat(ur.getEmail()).isNull();
    }
}
