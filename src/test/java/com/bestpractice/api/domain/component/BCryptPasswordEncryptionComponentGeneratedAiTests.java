package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnsEncodedPassword() {
        // GIVEN
        String rawPassword = "TestPassword123";

        // WHEN
        String encodedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "TestPassword123";
        String encodedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "TestPassword123";
        String differentRawPassword = "DifferentPassword123";
        String encodedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(differentRawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isFalse();
    }
}
