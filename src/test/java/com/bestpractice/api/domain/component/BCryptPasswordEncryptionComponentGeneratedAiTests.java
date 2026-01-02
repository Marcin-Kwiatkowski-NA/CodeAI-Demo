package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void encodePassword_shouldReturnEncodedPassword_whenRawPasswordIsValid() {
        // GIVEN
        String rawPassword = "mySecret";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(component.matchedPassword(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void encodePassword_shouldHandleEmptyString() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(component.matchedPassword(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void encodePassword_shouldThrowException_whenRawPasswordIsNull() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.encodePassword(rawPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password must not be null");
    }

    @Test
    void encodePassword_shouldEncodeSpecialCharacters() {
        // GIVEN
        String rawPassword = "P@ssw0rd!#%&";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(component.matchedPassword(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void matchedPassword_shouldReturnTrue_whenPasswordsMatch() {
        // GIVEN
        String rawPassword = "correctPassword";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean matches = component.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isTrue();
    }

    @Test
    void matchedPassword_shouldReturnFalse_whenPasswordsDoNotMatch() {
        // GIVEN
        String rawPassword = "wrongPassword";
        String encodedPassword = component.encodePassword("correctPassword");

        // WHEN
        boolean matches = component.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isFalse();
    }

    @Test
    void matchedPassword_shouldThrowException_whenRawPasswordIsNull() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = component.encodePassword("anyPassword");

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password must not be null");
    }

    @Test
    void matchedPassword_shouldThrowException_whenEncodedPasswordIsNull() {
        // GIVEN
        String rawPassword = "anyPassword";
        String encodedPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Encoded password must not be null");
    }
}
