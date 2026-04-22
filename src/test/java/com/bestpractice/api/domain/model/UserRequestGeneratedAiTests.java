package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        userRequest.setPassword("plainPassword");

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
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = null;
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
    }

    @Test
    void testConvertThrowsNullPointerExceptionWhenRequestIsNull() {
        // GIVEN
        UserRequest nullRequest = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            nullRequest.convert("1", "pw");
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
        assertEquals("", user.getUsername());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        // GIVEN
        userRequest.setUsername("user@#");
        userRequest.setEmail("email@domain.com");
        userRequest.setPassword("p@ssw0rd!");
        String id = "ID@123";
        String encodedPassword = "encoded@#";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("ID@123", user.getId());
        assertEquals("user@#", user.getUsername());
        assertEquals("email@domain.com", user.getEmail());
        assertEquals("encoded@#", user.getPassword());
    }
}
