package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SECURITY-SENSITIVE: This test class verifies password encryption and matching logic.
 */
@ExtendWith(MockitoExtension.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        boolean validPrefix = encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$");
        assertTrue(validPrefix);
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
        String encodedPassword = encryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

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
    void givenNullRawPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = encryptionComponent.encodePassword("somePassword");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenValidPassword_whenEncodePassword_thenProducesDifferentHashesEachTime() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encodedPassword1 = encryptionComponent.encodePassword(rawPassword);
        String encodedPassword2 = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword1);
        assertNotNull(encodedPassword2);
        assertNotEquals(encodedPassword1, encodedPassword2);
        assertTrue(new BCryptPasswordEncoder().matches(rawPassword, encodedPassword1));
        assertTrue(new BCryptPasswordEncoder().matches(rawPassword, encodedPassword2));
    }

    @Test
    void givenEncodedPassword_whenMatchedPassword_thenReturnTrueUsingExternalEncoder() {
        // GIVEN
        String rawPassword = "securePassword123";
        BCryptPasswordEncoder externalEncoder = new BCryptPasswordEncoder();
        String encodedPassword = externalEncoder.encode(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenDifferentPassword_whenMatchedPassword_thenReturnFalseUsingExternalEncoder() {
        // GIVEN
        String rawPassword = "securePassword123";
        BCryptPasswordEncoder externalEncoder = new BCryptPasswordEncoder();
        String encodedPassword = externalEncoder.encode("anotherPassword");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }
}
