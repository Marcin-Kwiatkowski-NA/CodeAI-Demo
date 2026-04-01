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
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encryptedPassword);
        assertFalse(encryptedPassword.equals(rawPassword));
        assertTrue(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean result = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertTrue(result);
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String differentPassword = "differentPassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean result = encryptionComponent.matchedPassword(differentPassword, encryptedPassword);

        // THEN
        assertFalse(result);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encryptedPassword = null;

        // WHEN
        boolean result = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertFalse(result);
    }

    @Test
    void givenEmptyRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encryptedPassword);
        assertFalse(encryptedPassword.isEmpty());
        assertTrue(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$"));
    }

    @Test
    void givenEmptyEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encryptedPassword = "";

        // WHEN
        boolean result = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertFalse(result);
    }

    @Test
    void givenValidPasswordEncoderInstance_whenEncodePassword_thenUseBCryptImplementation() {
        // GIVEN
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "securePassword123";

        // WHEN
        String encryptedPassword = encoder.encode(rawPassword);

        // THEN
        assertNotNull(encryptedPassword);
        assertTrue(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$"));
    }

    @Test
    void givenNullValues_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encryptedPassword = null;

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}
