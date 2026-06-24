package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * SECURITY-SENSITIVE: This class tests password encryption and matching logic.
 * It ensures that password hashing and verification behave correctly under various conditions.
 */
@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenValidPassword_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "securePassword123"; // security-sensitive

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "securePassword123"; // security-sensitive
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "securePassword123"; // security-sensitive
        String encodedPassword = encryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(false, matches);
    }

    @Test
    void givenWhitespaceOnlyPassword_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "   ";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenWhitespaceOnlyPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "   ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenSingleCharacterPassword_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "a";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenSingleCharacterPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "a";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenVeryLongPassword_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String rawPassword = sb.toString();

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenVeryLongPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("x");
        }
        String rawPassword = sb.toString();
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,./<>?";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithSpecialCharacters_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "!@#$%^&*()_+-=[]{}|;':,./<>?";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "パスワード😊";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithUnicodeCharacters_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "パスワード😊";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "  password  ";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithLeadingAndTrailingSpaces_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "  password  ";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithMixedCase_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "PasswordCaseSensitive";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithMixedCase_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "PasswordCaseSensitive";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithNumericCharacters_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "1234567890";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithNumericCharacters_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "1234567890";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenDifferentCasePasswords_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "Password";
        String encodedPassword = encryptionComponent.encodePassword("password");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(false, matches);
    }

    @Test
    void givenPasswordWithTrailingNewline_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "password\n";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithTrailingNewline_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "password\n";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithTabCharacter_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "pass\tword";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithTabCharacter_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "pass\tword";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }

    @Test
    void givenPasswordWithRepeatedCharacters_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void givenPasswordWithRepeatedCharacters_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertEquals(true, matches);
    }
}
