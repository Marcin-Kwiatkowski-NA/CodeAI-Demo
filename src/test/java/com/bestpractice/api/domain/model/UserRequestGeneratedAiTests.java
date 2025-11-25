package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
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
    void testGetAndSetUsername() {
        // GIVEN
        String username = "testUser";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(username);
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertThat(userRequest.getEmail()).isEqualTo(email);
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertThat(userRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testConvert() {
        // GIVEN
        String id = "123";
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
