package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
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
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .isNotEqualTo(rawPassword)
                .contains("$2a$");
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
    void givenWhitespaceOnlyPassword_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "   ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenSingleCharacterPassword_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "a";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenLongPassword_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "a".repeat(1000);
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
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
    void givenPasswordWithSpecialCharacters_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "パスワード😊";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "パスワード😊";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "  password  ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "  password  ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithMixedCase_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "PasswordCaseSensitive";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenPasswordWithMixedCase_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "PasswordCaseSensitive";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithNumericCharacters_whenEncodePassword_thenReturnHashedPassword() {
        String rawPassword = "1234567890";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        Assertions.assertThat(encodedPassword)
                .isNotNull()
                .isNotEmpty()
                .contains("$2a$")
                .isNotEqualTo(rawPassword);
    }

    @Test
    void givenPasswordWithNumericCharacters_whenMatchedPassword_thenReturnTrue() {
        String rawPassword = "1234567890";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(true, matches);
    }

    @Test
    void givenDifferentCasePasswords_whenMatchedPassword_thenReturnFalse() {
        String rawPassword = "PasswordCaseSensitive";
        String encodedPassword = encryptionComponent.encodePassword("passwordcasesensitive");
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertEquals(false, matches);
    }
}
