package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void givenValidUsername_whenSetUsername_thenUsernameIsSetCorrectly() {
        // GIVEN
        String username = "testUser";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(username);
    }

    @Test
    void givenValidEmail_whenSetEmail_thenEmailIsSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertThat(userRequest.getEmail()).isEqualTo(email);
    }

    @Test
    void givenValidPassword_whenSetPassword_thenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertThat(userRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void givenValidData_whenConvert_thenUserIsCreatedCorrectly() {
        // GIVEN
        String id = "12345";
        String encodedPassword = "encodedPassword";
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("securePassword");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
        assertThat(user.getUsername()).isEqualTo(userRequest.getUsername());
        assertThat(user.getEmail()).isEqualTo(userRequest.getEmail());
    }
}
