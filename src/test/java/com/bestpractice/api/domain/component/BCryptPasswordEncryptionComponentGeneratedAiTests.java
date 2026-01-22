package com.bestpractice.api.domain.component;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent passwordEncryptionComponent;

    @BeforeEach
    void setUp() {
        // GIVEN: a fresh instance of the component
        passwordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_returnsEncryptedPassword() {
        // GIVEN
        String rawPassword = "SecurePass123!";

        // WHEN
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");
    }

    @Test
    void encodePassword_generatesDifferentHashesForSamePassword() {
        // GIVEN
        String rawPassword = "SamePassword";

        // WHEN
        String firstHash = passwordEncryptionComponent.encodePassword(rawPassword);
        String secondHash = passwordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(firstHash).isNotEqualTo(secondHash);
    }

    @Test
    void encodePassword_throwsExceptionWhenPasswordIsNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        // THEN
        assertThatThrownBy(() -> passwordEncryptionComponent.encodePassword(nullPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password must not be null");
    }

    @Test
    void matchedPassword_returnsTrueForMatchingPasswords() {
        // GIVEN
        String rawPassword = "MatchingPass";
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = passwordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isTrue();
    }

    @Test
    void matchedPassword_returnsFalseForNonMatchingPasswords() {
        // GIVEN
        String rawPassword = "PasswordOne";
        String wrongRawPassword = "PasswordTwo";
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = passwordEncryptionComponent.matchedPassword(wrongRawPassword, encodedPassword);

        // THEN
        assertThat(matches).isFalse();
    }

    @Test
    void matchedPassword_throwsExceptionWhenRawPasswordIsNull() {
        // GIVEN
        String nullRawPassword = null;
        String encodedPassword = passwordEncryptionComponent.encodePassword("AnyPassword");

        // WHEN
        // THEN
        assertThatThrownBy(() -> passwordEncryptionComponent.matchedPassword(nullRawPassword, encodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password must not be null");
    }

    @Test
    void matchedPassword_throwsExceptionWhenEncryptedPasswordIsNull() {
        // GIVEN
        String rawPassword = "AnyPassword";
        String nullEncodedPassword = null;

        // WHEN
        // THEN
        assertThatThrownBy(() -> passwordEncryptionComponent.matchedPassword(rawPassword, nullEncodedPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Password must not be null");
    }
}
