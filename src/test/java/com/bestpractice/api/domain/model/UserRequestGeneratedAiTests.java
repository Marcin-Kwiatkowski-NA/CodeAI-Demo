package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("securePassword");
    }

    @Test
    void testGetAndSetUsername() {
        // GIVEN
        String expectedUsername = "newUser";

        // WHEN
        userRequest.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, userRequest.getUsername());
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        userRequest.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, userRequest.getEmail());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        userRequest.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, userRequest.getPassword());
    }

    @Test
    void testConvertCreatesUserCorrectly() {
        // GIVEN
        String id = "12345";
        String encodedPassword = "encodedSecurePassword";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void testConvertWithNullValues() {
        // GIVEN
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = "id-null";
        String encodedPassword = "encoded-null";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void testConvertHandlesNullIdGracefully() {
        // GIVEN
        String id = null;
        String encodedPassword = "encodedSecurePassword";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertNull(user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }

    @Test
    void testConvertHandlesNullEncodedPasswordGracefully() {
        // GIVEN
        String id = "12345";
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertNull(user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }

    @Test
    void testConvertThrowsExceptionWhenUserRequestFieldsAreNullAndUsedUnsafely() {
        // GIVEN
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = "id-test";
        String encodedPassword = "encoded-test";

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: Accessing null fields unsafely
            User user = userRequest.convert(id, encodedPassword);
            user.getUsername().length();
        });
    }
}
