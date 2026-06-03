package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

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
        String expectedUsername = "newUser";
        userRequest.setUsername(expectedUsername);
        assertEquals(expectedUsername, userRequest.getUsername());
    }

    @Test
    void testGetAndSetEmail() {
        String expectedEmail = "new@example.com";
        userRequest.setEmail(expectedEmail);
        assertEquals(expectedEmail, userRequest.getEmail());
    }

    @Test
    void testGetAndSetPassword() {
        String expectedPassword = "newPassword";
        userRequest.setPassword(expectedPassword);
        assertEquals(expectedPassword, userRequest.getPassword());
    }

    @Test
    void testConvertCreatesUserCorrectly() {
        String id = "12345";
        String encodedPassword = "encodedPw";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals(id, user.getId());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = "id-null";
        String encodedPassword = "encoded-null";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
    }

    @Test
    void testSetUsernameWithEmptyString() {
        String emptyUsername = "";
        userRequest.setUsername(emptyUsername);
        assertEquals(emptyUsername, userRequest.getUsername());
    }

    @Test
    void testSetUsernameWithWhitespaceOnly() {
        String whitespaceUsername = "   ";
        userRequest.setUsername(whitespaceUsername);
        assertEquals(whitespaceUsername, userRequest.getUsername());
    }

    @Test
    void testSetEmailWithWhitespaceOnly() {
        String whitespaceEmail = "   ";
        userRequest.setEmail(whitespaceEmail);
        assertEquals(whitespaceEmail, userRequest.getEmail());
    }

    @Test
    void testSetPasswordWithWhitespaceOnly() {
        String whitespacePassword = "   ";
        userRequest.setPassword(whitespacePassword);
        assertEquals(whitespacePassword, userRequest.getPassword());
    }

    @Test
    void testConvertWithEmptyIdAndPassword() {
        String id = "";
        String encodedPassword = "";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("", user.getId());
        assertEquals("", user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }

    @Test
    void testConvertWithWhitespaceIdAndPassword() {
        String id = "   ";
        String encodedPassword = "   ";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("   ", user.getId());
        assertEquals("   ", user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }

    @Test
    void testConvertWithSingleCharacterValues() {
        userRequest.setUsername("a");
        userRequest.setEmail("b@c");
        userRequest.setPassword("d");
        String id = "1";
        String encodedPassword = "2";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("1", user.getId());
        assertEquals("a", user.getUsername());
        assertEquals("b@c", user.getEmail());
        assertEquals("2", user.getPassword());
    }

    @Test
    void testConvertWithLongStrings() {
        String longString = "x".repeat(1000);
        userRequest.setUsername(longString);
        userRequest.setEmail(longString + "@example.com");
        userRequest.setPassword(longString);
        String id = longString;
        String encodedPassword = longString;
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals(longString, user.getId());
        assertEquals(longString, user.getUsername());
        assertEquals(longString + "@example.com", user.getEmail());
        assertEquals(longString, user.getPassword());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        userRequest.setUsername("user!@#");
        userRequest.setEmail("email+test@example.com");
        userRequest.setPassword("pass$%^");
        String id = "id*&";
        String encodedPassword = "pw()";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("id*&", user.getId());
        assertEquals("user!@#", user.getUsername());
        assertEquals("email+test@example.com", user.getEmail());
        assertEquals("pw()", user.getPassword());
    }

    @Test
    void testConvertWithUnicodeCharacters() {
        userRequest.setUsername("ユーザー");
        userRequest.setEmail("テスト@example.com");
        userRequest.setPassword("パスワード");
        String id = "識別子";
        String encodedPassword = "暗号";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("識別子", user.getId());
        assertEquals("ユーザー", user.getUsername());
        assertEquals("テスト@example.com", user.getEmail());
        assertEquals("暗号", user.getPassword());
    }

    @Test
    void testConvertWithEmojiCharacters() {
        userRequest.setUsername("😀User");
        userRequest.setEmail("emoji@example.com");
        userRequest.setPassword("🔒Pass");
        String id = "🆔";
        String encodedPassword = "🔑";
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals("🆔", user.getId());
        assertEquals("😀User", user.getUsername());
        assertEquals("emoji@example.com", user.getEmail());
        assertEquals("🔑", user.getPassword());
    }

    @Test
    void testConvertWithNullIdAndPassword() {
        String id = null;
        String encodedPassword = null;
        User user = userRequest.convert(id, encodedPassword);
        assertThat(user).isNotNull();
        assertEquals(null, user.getId());
        assertEquals(null, user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }
}
