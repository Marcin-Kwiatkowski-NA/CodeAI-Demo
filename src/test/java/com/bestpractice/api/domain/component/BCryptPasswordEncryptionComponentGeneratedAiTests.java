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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ShouldReturnEncryptedPassword() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2"));
    }

    @Test
    void matchedPassword_ShouldReturnTrueForMatchingPasswords() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertTrue(matches);
    }

    @Test
    void matchedPassword_ShouldReturnFalseForNonMatchingPasswords() {
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword("differentPassword456");
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldHandleEmptyString() {
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void matchedPassword_ShouldHandleEmptyStringComparison() {
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertTrue(matches);
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
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }

    @Test
    void matchedPassword_ShouldThrowException_WhenRawPasswordIsNull() {
        String rawPassword = null;
        String encryptedPassword = encryptionComponent.encodePassword("securePassword123");
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}
