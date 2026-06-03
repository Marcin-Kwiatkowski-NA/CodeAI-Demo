package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenValidPassword_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword("differentPassword");
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(false, matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowIllegalArgumentException() {
        String rawPassword = null;
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenEmptyRawPassword_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenWhitespaceOnlyPassword_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "   ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenSingleCharacterPassword_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "a";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenLongPassword_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "a".repeat(1000);
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenWhitespaceOnlyPassword_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "   ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenSingleCharacterPassword_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "a";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenLongPassword_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "a".repeat(1000);
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenEmptyEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        String rawPassword = "securePassword123";
        String encodedPassword = "";
        boolean result = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(false, result);
    }

    @Test
    void givenWhitespaceOnlyEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        String rawPassword = "securePassword123";
        String encodedPassword = "   ";
        boolean result = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(false, result);
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenEncodePassword_thenReturnValidHash() {
        String rawPassword = "パスワード😊";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "パスワード😊";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "  securePassword123  ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithDifferentCase_whenMatchedPassword_thenReturnFalse() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword("SecurePassword123");
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(false, matches);
    }
}
