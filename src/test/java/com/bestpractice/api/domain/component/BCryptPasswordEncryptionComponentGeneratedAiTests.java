package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        // Corrected: Initialize the actual component instead of mocking the PasswordEncoder
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncodedPassword() {
        // GIVEN
        String rawPassword = "testPassword";

        // WHEN
        String result = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isNotEqualTo(rawPassword); // Encoded password should not match raw password
    }

    @Test
    void givenRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean result = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void givenRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean result = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isFalse();
    }
}
