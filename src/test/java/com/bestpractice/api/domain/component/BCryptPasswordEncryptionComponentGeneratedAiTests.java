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
        String differentPassword = "otherPassword456";
        String encodedPassword = encryptionComponent.encodePassword(differentPassword);
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldHandleEmptyStringGracefully() {
        String rawPassword = "";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void encodePassword_ShouldThrowExceptionForNullInput() {
        String rawPassword = null;
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void matchedPassword_ShouldThrowExceptionForNullInputs() {
        String rawPassword = null;
        String encodedPassword = null;
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void matchedPassword_ShouldThrowExceptionForNullRawPassword() {
        String rawPassword = null;
        String encodedPassword = encryptionComponent.encodePassword("validPassword");
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void matchedPassword_ShouldReturnFalseForNullEncodedPassword() {
        String rawPassword = "securePassword123";
        String encodedPassword = null;
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);
        assertFalse(matches);
    }
}
