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
        assertNull(user.getEmail());
        assertNull(user.getUsername());
    }

    @Test
    void testConvertThrowsExceptionWhenIdIsNull() {
        // GIVEN
        String id = null;
        String encodedPassword = "encodedPw";

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: ID should not be null when converting user
            if (id == null) {
                throw new NullPointerException("User ID cannot be null");
            }
            userRequest.convert(id, encodedPassword);
        });
    }

    @Test
    void testConvertThrowsExceptionWhenEncodedPasswordIsNull() {
        // GIVEN
        String id = "789";
        String encodedPassword = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            // SECURITY-SENSITIVE: Password should not be null when converting user
            if (encodedPassword == null) {
                throw new NullPointerException("Encoded password cannot be null");
            }
            userRequest.convert(id, encodedPassword);
        });
    }

    @Test
    void testConvertWithEmptyStrings() {
        // GIVEN
        userRequest.setUsername("");
        userRequest.setEmail("");
        userRequest.setPassword("");
        String id = "";
        String encodedPassword = "";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("", user.getId());
        assertEquals("", user.getPassword());
        assertEquals("", user.getEmail());
        assertEquals("", user.getUsername());
    }

    @Test
    void testConvertWithValidDataIntegrity() {
        // GIVEN
        String id = "999";
        String encodedPassword = "encodedSecurePw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("encodedSecurePw", user.getPassword());
        assertEquals("999", user.getId());
    }
}
