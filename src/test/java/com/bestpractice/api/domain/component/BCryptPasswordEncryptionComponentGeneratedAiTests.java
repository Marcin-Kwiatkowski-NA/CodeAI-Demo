package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: password handling
        String differentPassword = "differentPassword456"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(differentPassword, encodedPassword);

        // THEN
        assertEquals(false, matches);
    }

    @Test
    void givenWhitespaceOnlyPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "   "; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenSingleCharacterPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "a"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenVeryLongPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String rawPassword = sb.toString(); // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenWhitespaceOnlyPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "   "; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenSingleCharacterPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "a"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenVeryLongPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String rawPassword = sb.toString(); // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,.<>?/"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "パスワード😊"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "パスワード😊"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "  password  "; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "  password  "; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithMixedCase_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "PasswordCaseSensitive"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithMixedCase_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "PasswordCaseSensitive"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenDifferentCasePassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "PasswordCaseSensitive"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword("passwordcasesensitive", encodedPassword);

        // THEN
        assertEquals(false, matches);
    }

    @Test
    void givenSamePasswordEncodedTwice_whenCompared_thenReturnDifferentHashes() {
        // GIVEN
        String rawPassword = "repeatPassword"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword1 = encryptionComponent.encodePassword(rawPassword);
        String encodedPassword2 = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword1).isNotEqualTo(encodedPassword2);
        assertThat(encryptionComponent.matchedPassword(rawPassword, encodedPassword1)).isTrue();
        assertThat(encryptionComponent.matchedPassword(rawPassword, encodedPassword2)).isTrue();
    }

    @Test
    void givenEmptyStringPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = ""; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenEmptyStringPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = ""; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }
}
