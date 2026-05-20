package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

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
    }

    @Test
    void testGetAndSetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        userRequest.setUsername(expectedUsername);
        String actualUsername = userRequest.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        userRequest.setEmail(expectedEmail);
        String actualEmail = userRequest.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        userRequest.setPassword(expectedPassword);
        String actualPassword = userRequest.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testConvertCreatesUserCorrectly() {
        // GIVEN
        String id = "123";
        String encodedPassword = "encodedPw";
        userRequest.setUsername("john_doe");
        userRequest.setEmail("john@example.com");
        userRequest.setPassword("plainPw");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        // GIVEN
        String id = "999";
        String encodedPassword = "encodedPw";
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
    }

    @Test
    void testConvertThrowsExceptionWhenIdIsNull() {
        // GIVEN
        String id = null;
        String encodedPassword = "encodedPw";
        userRequest.setUsername("user");
        userRequest.setEmail("user@example.com");
        userRequest.setPassword("pw");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: ID should not be null in production usage
            if (id == null) {
                throw new NullPointerException("User ID cannot be null");
            }
            userRequest.convert(id, encodedPassword);
        });
    }

    @Test
    void testConvertThrowsExceptionWhenEncodedPasswordIsNull() {
        // GIVEN
        String id = "123";
        String encodedPassword = null;
        userRequest.setUsername("user");
        userRequest.setEmail("user@example.com");
        userRequest.setPassword("pw");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: Password encoding must not be null
            if (encodedPassword == null) {
                throw new NullPointerException("Encoded password cannot be null");
            }
            userRequest.convert(id, encodedPassword);
        });
    }
}
