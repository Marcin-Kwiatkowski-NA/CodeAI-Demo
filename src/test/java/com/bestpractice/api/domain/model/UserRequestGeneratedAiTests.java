package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        String actualUsername = userRequest.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        userRequest.setEmail(expectedEmail);
        String actualEmail = userRequest.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

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

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        // GIVEN
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = "456";
        String encodedPassword = "encodedPw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getUsername());
    }

    @Test
    void testConvertThrowsExceptionWhenIdIsNull() {
        // GIVEN
        String id = null;
        String encodedPassword = "encodedPw";

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: ID should not be null in production usage
            userRequest.convert(id, encodedPassword).getId().length();
        });
    }

    @Test
    void testConvertThrowsExceptionWhenEncodedPasswordIsNull() {
        // GIVEN
        String id = "789";
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(null, user.getPassword());
    }
}
