package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";
        userRequest.setUsername(expectedUsername);

        // WHEN
        String actualUsername = userRequest.getUsername();

        // THEN
        assertThat(actualUsername).isEqualTo(expectedUsername);
    }

    @Test
    void testSetUsername() {
        // GIVEN
        String expectedUsername = "newUser";

        // WHEN
        userRequest.setUsername(expectedUsername);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(expectedUsername);
    }

    @Test
    void testGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";
        userRequest.setEmail(expectedEmail);

        // WHEN
        String actualEmail = userRequest.getEmail();

        // THEN
        assertThat(actualEmail).isEqualTo(expectedEmail);
    }

    @Test
    void testSetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        userRequest.setEmail(expectedEmail);

        // THEN
        assertThat(userRequest.getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    void testGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";
        userRequest.setPassword(expectedPassword);

        // WHEN
        String actualPassword = userRequest.getPassword();

        // THEN
        assertThat(actualPassword).isEqualTo(expectedPassword);
    }

    @Test
    void testSetPassword() {
        // GIVEN
        String expectedPassword = "newSecurePassword";

        // WHEN
        userRequest.setPassword(expectedPassword);

        // THEN
        assertThat(userRequest.getPassword()).isEqualTo(expectedPassword);
    }

    @Test
    void testConvert() {
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
