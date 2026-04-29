package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * SECURITY-SENSITIVE: This test class validates password encryption and matching logic.
 * It ensures that password handling is secure and behaves as expected.
 */
@ExtendWith(MockitoExtension.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertFalse(encodedPassword.equals(rawPassword));
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String differentPassword = "differentPassword456";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(differentPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullValues_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenEmptyPassword_whenEncodePassword_thenReturnValidHash() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void givenEncodedPasswordFromBCrypt_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "securePassword123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenDifferentEncodedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = new BCryptPasswordEncoder().encode("differentPassword456");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenSamePasswordEncodedTwice_whenCompared_thenHashesAreDifferent() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encoded1 = encryptionComponent.encodePassword(rawPassword);
        String encoded2 = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encoded1);
        assertNotNull(encoded2);
        assertFalse(encoded1.equals(encoded2)); // BCrypt should produce different hashes for same input
    }
}
