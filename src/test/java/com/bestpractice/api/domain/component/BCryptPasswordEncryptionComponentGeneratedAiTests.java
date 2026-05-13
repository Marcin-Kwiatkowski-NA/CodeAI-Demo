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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ShouldReturnEncryptedString() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertFalse(encodedPassword.equals(rawPassword));
        assertTrue(encodedPassword.startsWith("$2"));
    }

    @Test
    void matchedPassword_ShouldReturnTrueForMatchingPasswords() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void matchedPassword_ShouldReturnFalseForNonMatchingPasswords() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = encryptionComponent.encodePassword("differentPassword456");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void encodePassword_ShouldHandleEmptyStringGracefully() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(encodedPassword.startsWith("$2"));
    }

    @Test
    void matchedPassword_ShouldThrowExceptionForNullRawPassword() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = encryptionComponent.encodePassword("somePassword");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void matchedPassword_ShouldReturnFalseForNullEncryptedPassword() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encryptedPassword = null;

        // WHEN
        boolean result = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertFalse(result);
    }

    @Test
    void encodePassword_ShouldThrowExceptionForNullInput() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }
}
