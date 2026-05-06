package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Security-sensitive: This test class validates password encryption and matching logic.
 */
@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ShouldReturnEncryptedPassword() {
        // GIVEN: a raw password to encode (security-sensitive operation)
        String rawPassword = "securePassword123";

        // WHEN: encoding the password
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encoded password should not be null and should differ from the raw password
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void matchedPassword_ShouldReturnTrueForMatchingPasswords() {
        // GIVEN: a raw password and its encoded version (security-sensitive operation)
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encoded one
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: the match should be true
        assertTrue(matches);
    }

    @Test
    void matchedPassword_ShouldReturnFalseForNonMatchingPasswords() {
        // GIVEN: a raw password and an encoded password of a different raw value (security-sensitive operation)
        String rawPassword = "securePassword123";
        String differentRawPassword = "differentPassword456";
        String encodedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encoded one
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: the match should be false
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldHandleEmptyStringGracefully() {
        // GIVEN: an empty raw password (security-sensitive operation)
        String rawPassword = "";

        // WHEN: encoding the empty password
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encoded password should not be null or empty
        assertNotNull(encodedPassword);
        assertFalse(encodedPassword.isEmpty());
    }

    @Test
    void matchedPassword_ShouldThrowExceptionForNullInputs() {
        // GIVEN: null raw and encoded passwords (security-sensitive operation)
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN & THEN: expect IllegalArgumentException when matching null values
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void encodePassword_ShouldThrowExceptionForNullInput() {
        // GIVEN: null raw password (security-sensitive operation)
        String rawPassword = null;

        // WHEN & THEN: expect IllegalArgumentException when encoding null value
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void matchedPassword_ShouldReturnFalseWhenEncryptedPasswordIsInvalidFormat() {
        // GIVEN: a valid raw password and an invalid encrypted password format (security-sensitive operation)
        String rawPassword = "securePassword123";
        String invalidEncryptedPassword = "invalidFormatHash";

        // WHEN: checking if the raw password matches the invalid encrypted one
        boolean matches = encryptionComponent.matchedPassword(rawPassword, invalidEncryptedPassword);

        // THEN: the match should be false
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldProduceDifferentHashesForSameInput() {
        // GIVEN: the same raw password (security-sensitive operation)
        String rawPassword = "securePassword123";

        // WHEN: encoding the same password twice
        String encodedPassword1 = encryptionComponent.encodePassword(rawPassword);
        String encodedPassword2 = encryptionComponent.encodePassword(rawPassword);

        // THEN: the two encoded passwords should not be equal due to salting
        assertNotEquals(encodedPassword1, encodedPassword2);
    }
}
