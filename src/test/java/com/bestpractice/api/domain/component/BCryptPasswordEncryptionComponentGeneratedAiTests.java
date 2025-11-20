package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_ShouldReturnEncryptedPassword_WhenRawPasswordIsProvided() {
        // GIVEN: A raw password
        String rawPassword = "TestPassword123";

        // WHEN: The password is encoded
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN: The encrypted password should not be null and should differ from the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void matchedPassword_ShouldReturnTrue_WhenRawPasswordMatchesEncryptedPassword() {
        // GIVEN: A raw password and its encrypted version
        String rawPassword = "TestPassword123";
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN: The raw password is checked against the encrypted password
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The match result should be true
        assertThat(isMatched).isTrue();
    }

    @Test
    void matchedPassword_ShouldReturnFalse_WhenRawPasswordDoesNotMatchEncryptedPassword() {
        // GIVEN: A raw password and a different encrypted password
        String rawPassword = "TestPassword123";
        String differentEncryptedPassword = new BCryptPasswordEncoder().encode("DifferentPassword456");

        // WHEN: The raw password is checked against the different encrypted password
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, differentEncryptedPassword);

        // THEN: The match result should be false
        assertThat(isMatched).isFalse();
    }
}
