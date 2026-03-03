package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
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
    void testEncodePasswordReturnsNonNullAndMatchesRaw() {
        // GIVEN
        String rawPassword = "SecurePassword123!";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(component.matchedPassword(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void testMatchedPasswordReturnsTrueForMatchingPasswords() {
        // GIVEN
        String rawPassword = "Another$Pass456";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean matches = component.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isTrue();
    }

    @Test
    void testMatchedPasswordReturnsFalseForNonMatchingPasswords() {
        // GIVEN
        String rawPassword = "PasswordOne";
        String otherRawPassword = "PasswordTwo";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean matches = component.matchedPassword(otherRawPassword, encodedPassword);

        // THEN
        assertThat(matches).isFalse();
    }

    @Test
    void testEncodePasswordWithEmptyString() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(component.matchedPassword(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    void testEncodePasswordWithNullThrowsException() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.encodePassword(rawPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot encode null");
    }

    @Test
    void testMatchedPasswordWithNullRawThrowsException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = component.encodePassword("SomePassword");

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot encode null");
    }

    @Test
    void testMatchedPasswordWithNullEncryptedThrowsException() {
        // GIVEN
        String rawPassword = "SomePassword";
        String encodedPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> component.matchedPassword(rawPassword, encodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot encode null");
    }
}
