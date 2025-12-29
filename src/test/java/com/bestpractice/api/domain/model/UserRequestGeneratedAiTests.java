package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserRequestGeneratedAiTests {

    @Test
    void shouldConvertValidUserRequestToUserWithCorrectValues() {
        // GIVEN
        String id = "1";
        String encodedPassword = "encodedSecurePassword";

        UserRequest validRequest = new UserRequest();
        validRequest.setUsername("testuser");
        validRequest.setEmail("test@example.com");
        validRequest.setPassword("securepassword123");

        // WHEN
        User convertedUser = validRequest.convert(id, encodedPassword);

        // THEN
        assertThat(convertedUser.getId()).isEqualTo(id);
        assertThat(convertedUser.getUsername()).isEqualTo("testuser");
        assertThat(convertedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(convertedUser.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        // GIVEN
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setEmail("test@example.com");
        invalidRequest.setPassword("securepassword123");

        // WHEN & THEN
        assertThatThrownBy(() -> invalidRequest.convert("1", "encodedPassword"))
                .hasMessageContaining("username");
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // GIVEN
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setUsername("testuser");
        invalidRequest.setPassword("securepassword123");

        // WHEN & THEN
        assertThatThrownBy(() -> invalidRequest.convert("1", "encodedPassword"))
                .hasMessageContaining("email");
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        // GIVEN
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setUsername("testuser");
        invalidRequest.setEmail("test@example.com");

        // WHEN & THEN
        assertThatThrownBy(() -> invalidRequest.convert("1", "encodedPassword"))
                .hasMessageContaining("password");
    }

    @Test
    void shouldHandleEmptyUsernameWithValidation() {
        // GIVEN
        UserRequest emptyUsernameRequest = new UserRequest();
        emptyUsernameRequest.setEmail("test@example.com");
        emptyUsernameRequest.setPassword("securepassword123");

        // WHEN & THEN
        assertThatThrownBy(() -> emptyUsernameRequest.convert("1", "encodedPassword"))
                .hasMessageContaining("username");
    }

    @Test
    void shouldHandleInvalidEmailFormat() {
        // GIVEN
        UserRequest invalidEmailRequest = new UserRequest();
        invalidEmailRequest.setUsername("testuser");
        invalidEmailRequest.setPassword("secure");
        invalidEmailRequest.setEmail("invalid-email");

        // WHEN & THEN
        assertThatThrownBy(() -> invalidEmailRequest.convert("1", "encodedPassword"))
                .hasMessageContaining("email");
    }

    @Test
    void shouldConvertUserRequestWithAllFieldsPresent() {
        // GIVEN
        String id = "uuid-123";
        String encodedPassword = "hashedPassword";

        UserRequest validRequest = new UserRequest();
        validRequest.setUsername("testuser");
        validRequest.setEmail("test@example.com");
        validRequest.setPassword("securepassword123");

        // WHEN
        User convertedUser = validRequest.convert(id, encodedPassword);

        // THEN
        assertThat(convertedUser.getId()).isEqualTo(id);
        assertThat(convertedUser.getUsername()).isEqualTo("testuser");
        assertThat(convertedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(convertedUser.getPassword()).isEqualTo(encodedPassword);
    }
}
