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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: password handling

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
        String rawPassword = "securePassword123"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123"; // Security-sensitive: password handling
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
    void givenNullInputs_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenEmptyPassword_whenEncodePassword_thenReturnValidHash() {
        // GIVEN
        String rawPassword = ""; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertFalse(encodedPassword.isEmpty());
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void givenEmptyPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = ""; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenEncodedPassword_whenMatchedWithDifferentRawPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "password1"; // Security-sensitive: password handling
        String encodedPassword = encryptionComponent.encodePassword("password2");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenValidPasswordEncoderInstance_whenEncodePassword_thenConsistentBehavior() {
        // GIVEN
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "testPassword"; // Security-sensitive: password handling

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
