package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // Reset shared state before each test
        userResponse = null;
    }

    @Test
    void testGettersReturnCorrectValues() {
        // GIVEN
        String id = "123";
        String username = "john_doe";
        String email = "john@example.com";
        UserResponse response = new UserResponse(id, username, email);

        // WHEN
        String returnedId = response.getId();
        String returnedUsername = response.getUsername();
        String returnedEmail = response.getEmail();

        // THEN
        assertThat(returnedId).isEqualTo(id);
        assertThat(returnedUsername).isEqualTo(username);
        assertThat(returnedEmail).isEqualTo(email);
    }

    @Test
    void testConstructorAllowsNullValues() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;

        // WHEN
        UserResponse response = new UserResponse(id, username, email);

        // THEN
        assertThat(response.getId()).isNull();
        assertThat(response.getUsername()).isNull();
        assertThat(response.getEmail()).isNull();
    }

    @Test
    void testImmutability() {
        // GIVEN
        String id = "456";
        String username = "jane_doe";
        String email = "jane@example.com";
        UserResponse response = new UserResponse(id, username, email);

        // WHEN
        String firstId = response.getId();
        String secondId = response.getId();
        String firstUsername = response.getUsername();
        String secondUsername = response.getUsername();
        String firstEmail = response.getEmail();
        String secondEmail = response.getEmail();

        // THEN
        assertThat(firstId).isEqualTo(secondId);
        assertThat(firstUsername).isEqualTo(secondUsername);
        assertThat(firstEmail).isEqualTo(secondEmail);
    }

    @Test
    void testEmptyStringValues() {
        // GIVEN
        String id = "";
        String username = "";
        String email = "";

        // WHEN
        UserResponse response = new UserResponse(id, username, email);

        // THEN
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getUsername()).isEqualTo(username);
        assertThat(response.getEmail()).isEqualTo(email);
    }
}
