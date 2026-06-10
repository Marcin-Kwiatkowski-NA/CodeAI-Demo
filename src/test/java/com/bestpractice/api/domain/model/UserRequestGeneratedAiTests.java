package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void testConvertWithEmptyIdAndPassword() {
        // GIVEN
        String id = "";
        String encodedPassword = "";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("", user.getId());
        assertEquals("", user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithWhitespaceIdAndPassword() {
        // GIVEN
        String id = "   ";
        String encodedPassword = "   ";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("   ", user.getId());
        assertEquals("   ", user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithLongStrings() {
        // GIVEN
        String longId = "a".repeat(1000);
        String longPassword = "b".repeat(1000);
        userRequest.setUsername("user".repeat(500));
        userRequest.setEmail("email@example.com".repeat(100));

        // WHEN
        User user = userRequest.convert(longId, longPassword);

        // THEN
        assertNotNull(user);
        assertEquals(longId, user.getId());
        assertEquals(longPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    void testConvertWithSingleCharacterValues() {
        // GIVEN
        String id = "1";
        String encodedPassword = "p";
        userRequest.setUsername("u");
        userRequest.setEmail("e@e.com");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("1", user.getId());
        assertEquals("p", user.getPassword());
        assertEquals("e@e.com", user.getEmail());
        assertEquals("u", user.getUsername());
    }

    @Test
    void testConvertWithNullIdAndPassword() {
        // GIVEN
        String id = null;
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(null, user.getId());
        assertEquals(null, user.getPassword());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        // GIVEN
        String id = "!@#$%^&*()";
        String encodedPassword = "<>?/{}[]";
        userRequest.setUsername("specialUser");
        userRequest.setEmail("special@example.com");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("!@#$%^&*()", user.getId());
        assertEquals("<>?/{}[]", user.getPassword());
        assertEquals("special@example.com", user.getEmail());
        assertEquals("specialUser", user.getUsername());
    }

    @Test
    void testConvertWithUnicodeCharacters() {
        // GIVEN
        String id = "用户123";
        String encodedPassword = "密码456";
        userRequest.setUsername("测试");
        userRequest.setEmail("测试@example.com");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("用户123", user.getId());
        assertEquals("密码456", user.getPassword());
        assertEquals("测试@example.com", user.getEmail());
        assertEquals("测试", user.getUsername());
    }

    @Test
    void testConvertWithNumericStrings() {
        // GIVEN
        String id = String.valueOf(Integer.MAX_VALUE);
        String encodedPassword = String.valueOf(Integer.MIN_VALUE);
        userRequest.setUsername("numericUser");
        userRequest.setEmail("numeric@example.com");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals(String.valueOf(Integer.MAX_VALUE), user.getId());
        assertEquals(String.valueOf(Integer.MIN_VALUE), user.getPassword());
        assertEquals("numeric@example.com", user.getEmail());
        assertEquals("numericUser", user.getUsername());
    }

    @Test
    void testConvertWithZeroAndNegativeNumericStrings() {
        // GIVEN
        String id = "0";
        String encodedPassword = "-1";
        userRequest.setUsername("zeroUser");
        userRequest.setEmail("zero@example.com");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertNotNull(user);
        assertEquals("0", user.getId());
        assertEquals("-1", user.getPassword());
        assertEquals("zero@example.com", user.getEmail());
        assertEquals("zeroUser", user.getUsername());
    }
}
