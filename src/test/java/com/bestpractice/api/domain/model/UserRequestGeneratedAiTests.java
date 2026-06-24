package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        // GIVEN
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
    void testConvertHandlesEmptyFields() {
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
    void testSetUsernameWithEmptyString() {
        // GIVEN
        String username = "";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertEquals("", userRequest.getUsername());
    }

    @Test
    void testSetUsernameWithWhitespaceOnly() {
        // GIVEN
        String username = "   ";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertEquals("   ", userRequest.getUsername());
    }

    @Test
    void testSetEmailWithEmptyString() {
        // GIVEN
        String email = "";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertEquals("", userRequest.getEmail());
    }

    @Test
    void testSetEmailWithWhitespaceOnly() {
        // GIVEN
        String email = "   ";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertEquals("   ", userRequest.getEmail());
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN
        String password = "";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertEquals("", userRequest.getPassword());
    }

    @Test
    void testSetPasswordWithWhitespaceOnly() {
        // GIVEN
        String password = "   ";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertEquals("   ", userRequest.getPassword());
    }

    @Test
    void testConvertWithEmptyId() {
        // GIVEN
        String id = "";
        String encodedPassword = "encodedPw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("", user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithWhitespaceId() {
        // GIVEN
        String id = "   ";
        String encodedPassword = "encodedPw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("   ", user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithLongId() {
        // GIVEN
        String id = "a".repeat(1000);
        String encodedPassword = "encodedPw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void testConvertWithEmptyEncodedPassword() {
        // GIVEN
        String id = "123";
        String encodedPassword = "";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("", user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithWhitespaceEncodedPassword() {
        // GIVEN
        String id = "123";
        String encodedPassword = "   ";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("   ", user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        // GIVEN
        userRequest.setUsername("user!@#");
        userRequest.setEmail("email!@#");
        userRequest.setPassword("pass!@#");
        String id = "!@#";
        String encodedPassword = "$%^";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("!@#", user.getId());
        assertEquals("$%^", user.getPassword());
        assertEquals("email!@#", user.getEmail());
        assertEquals("user!@#", user.getUsername());
    }

    @Test
    void testConvertWithSingleCharacterValues() {
        // GIVEN
        userRequest.setUsername("u");
        userRequest.setEmail("e");
        userRequest.setPassword("p");
        String id = "i";
        String encodedPassword = "x";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("i", user.getId());
        assertEquals("x", user.getPassword());
        assertEquals("e", user.getEmail());
        assertEquals("u", user.getUsername());
    }

    @Test
    void testConvertWithBoundaryNumericStrings() {
        // GIVEN
        String id = String.valueOf(Integer.MAX_VALUE);
        String encodedPassword = String.valueOf(Integer.MIN_VALUE);

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(String.valueOf(Integer.MAX_VALUE), user.getId());
        assertEquals(String.valueOf(Integer.MIN_VALUE), user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithNullValuesInUserRequest() {
        // GIVEN
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = "789";
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
    void testConvertPreservesOriginalValues() {
        // GIVEN
        String id = "999";
        String encodedPassword = "encodedPw";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getUsername()).isEqualTo("testUser");
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
        assertThat(user.getId()).isEqualTo(id);
    }
}
