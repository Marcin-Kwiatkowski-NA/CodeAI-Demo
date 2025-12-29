package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class UserResponseGeneratedAiTests {

    private static final String EXPECTED_JSON = "{\"id\":\"123\",\"username\":\"john_doe\",\"email\":\"john@example.com\"}";

    @Test
    void givenValidUserResponse_whenGetId_thenReturnId() {
        // GIVEN
        UserResponse userResponse = new UserResponse("123", "john_doe", "john@example.com");

        // WHEN
        String actualId = userResponse.getId();

        // THEN
        assertThat(actualId).isEqualTo("123");
    }

    @Test
    void givenValidUserResponse_whenGetUsername_thenReturnUsername() {
        // GIVEN
        UserResponse userResponse = new UserResponse("123", "john_doe", "john@example.com");

        // WHEN
        String actualUsername = userResponse.getUsername();

        // THEN
        assertThat(actualUsername).isEqualTo("john_doe");
    }

    @Test
    void givenValidUserResponse_whenGetEmail_thenReturnEmail() {
        // GIVEN
        UserResponse userResponse = new UserResponse("123", "john_doe", "john@example.com");

        // WHEN
        String actualEmail = userResponse.getEmail();

        // THEN
        assertThat(actualEmail).isEqualTo("john@example.com");
    }

    @Test
    void givenNullUserResponse_whenGetId_thenReturnNull() {
        // GIVEN
        UserResponse userResponse = new UserResponse(null, null, null);

        // WHEN
        String actualId = userResponse.getId();

        // THEN
        assertThat(actualId).isNull();
    }

    @Test
    void givenEmptyUserResponse_whenGetId_thenReturnEmptyString() {
        // GIVEN
        UserResponse userResponse = new UserResponse("", "", "");

        // WHEN
        String actualId = userResponse.getId();

        // THEN
        assertThat(actualId).isEqualTo("");
    }

    @Test
    void givenValidUserResponse_whenToJsonString_thenReturnCorrectJson() throws JsonProcessingException {
        // GIVEN
        UserResponse userResponse = new UserResponse("123", "john_doe", "john@example.com");

        // WHEN
        String actualJson = new ObjectMapper().writeValueAsString(userResponse);

        // THEN
        assertThat(actualJson).isEqualTo(EXPECTED_JSON);
    }

    @Test
    void givenInvalidEmailFormat_whenCreatingUserResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new UserResponse("123", "test_user", "invalid-email"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("email");
    }

    @Test
    void givenNullUsername_whenCreatingUserResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new UserResponse("127", null, "test@example.com"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("username");
    }

    @Test
    void givenNullId_whenCreatingUserResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new UserResponse(null, "test_user", "test@example.com"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id");
    }

    @Test
    void givenEmptyId_whenCreatingUserResponse_thenThrowException() {
        // WHEN & THEN
        assertThatThrownBy(() -> new UserResponse("", "test_user", "test@example.com"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id");
    }
}
