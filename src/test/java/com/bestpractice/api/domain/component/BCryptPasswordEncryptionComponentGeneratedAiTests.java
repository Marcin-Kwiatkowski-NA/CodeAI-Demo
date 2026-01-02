package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_shouldReturnNonNullHash() {
        // GIVEN
        String rawPassword = "mySecretPassword";

        // WHEN
        String encoded = component.encodePassword(rawPassword);

        // THEN
        assertThat(encoded).isNotNull();
        assertThat(encoded).isNotEqualTo(rawPassword);
        assertThat(encoded).matches("^\\$2[abxy]\\$\\d{2}\\$[./A-Za-z0-9]{53}$");
    }

    @Test
    void encodePassword_shouldGenerateDifferentHashesForSamePassword() {
        // GIVEN
        String rawPassword = "samePassword";

        // WHEN
        String hash1 = component.encodePassword(rawPassword);
        String hash2 = component.encodePassword(rawPassword);

        // THEN
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void encodePassword_shouldThrowExceptionWhenRawPasswordIsNull() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.encodePassword(rawPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("rawPassword cannot be null");
    }

    @Test
    void matchedPassword_shouldReturnTrueForMatchingPasswords() {
        // GIVEN
        String rawPassword = "testPassword";
        String encoded = component.encodePassword(rawPassword);

        // WHEN
        boolean matches = component.matchedPassword(rawPassword, encoded);

        // THEN
        assertThat(matches).isTrue();
    }

    @Test
    void matchedPassword_shouldReturnFalseForNonMatchingPasswords() {
        // GIVEN
        String rawPassword = "testPassword";
        String wrongPassword = "wrongPassword";
        String encoded = component.encodePassword(rawPassword);

        // WHEN
        boolean matches = component.matchedPassword(wrongPassword, encoded);

        // THEN
        assertThat(matches).isFalse();
    }

    @Test
    void matchedPassword_shouldThrowExceptionWhenRawPasswordIsNull() {
        // GIVEN
        String rawPassword = null;
        String encoded = component.encodePassword("anyPassword");

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encoded))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("rawPassword cannot be null");
    }

    @Test
    void matchedPassword_shouldThrowExceptionWhenEncryptedPasswordIsNull() {
        // GIVEN
        String rawPassword = "anyPassword";
        String encoded = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encoded))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("encodedPassword cannot be null");
    }
}
