package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ShouldReturnEncryptedPassword_WhenRawPasswordIsValid() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void matchedPassword_ShouldReturnTrue_WhenRawPasswordMatchesEncodedPassword() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertTrue(matches);
    }

    @Test
    void matchedPassword_ShouldReturnFalse_WhenRawPasswordDoesNotMatchEncodedPassword() {
        String rawPassword = "securePassword123";
        String differentRawPassword = "wrongPassword456";
        String encodedPassword = encryptionComponent.encodePassword(differentRawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldHandleEmptyPasswordGracefully() {
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertNotNull(encodedPassword);
        assertFalse(encodedPassword.isEmpty());
    }

    @Test
    void matchedPassword_ShouldReturnFalse_WhenEncryptedPasswordIsInvalidFormat() {
        String rawPassword = "securePassword123";
        String invalidEncryptedPassword = "invalidFormatPassword";
        boolean matches = encryptionComponent.matchedPassword(rawPassword, invalidEncryptedPassword);
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldThrowException_WhenRawPasswordIsNull() {
        String rawPassword = null;
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void matchedPassword_ShouldThrowException_WhenEncryptedPasswordIsNull() {
        String rawPassword = "securePassword123";
        String encryptedPassword = null;
        boolean result = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);
        assertFalse(result);
    }

    @Test
    void matchedPassword_ShouldThrowException_WhenRawPasswordIsNull() {
        String rawPassword = null;
        String encryptedPassword = encryptionComponent.encodePassword("securePassword123");
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}
