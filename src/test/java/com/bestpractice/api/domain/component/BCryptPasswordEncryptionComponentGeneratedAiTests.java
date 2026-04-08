package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnsHashedPassword() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: test data only

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2"));
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: test data only
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: test data only
        String differentPassword = "differentPassword456"; // Security-sensitive: test data only
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(differentPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowsException() {
        // GIVEN
        String rawPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullValues_whenMatchedPassword_thenThrowsException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenEmptyPassword_whenEncodePassword_thenReturnsValidHash() {
        // GIVEN
        String rawPassword = ""; // Security-sensitive: test data only

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(encodedPassword.startsWith("$2"));
    }

    @Test
    void givenEncodedPassword_whenMatchedPasswordWithEmptyRaw_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword("somePassword");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenValidPassword_whenEncodePassword_thenEncodedPasswordIsDifferentEachTime() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: test data only

        // WHEN
        String encodedPassword1 = encryptionComponent.encodePassword(rawPassword);
        String encodedPassword2 = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword1);
        assertNotNull(encodedPassword2);
        assertNotEquals(encodedPassword1, encodedPassword2);
    }

    @Test
    void givenValidPassword_whenMatchedPassword_thenReturnsConsistentResults() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: test data only
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean firstMatch = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        boolean secondMatch = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(firstMatch);
        assertEquals(firstMatch, secondMatch);
    }
}
