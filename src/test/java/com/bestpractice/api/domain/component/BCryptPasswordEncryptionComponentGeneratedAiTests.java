package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent passwordEncryptionComponent;

    @BeforeEach
    void setUp() {
        passwordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnsEncodedPassword() {
        // GIVEN
        String rawPassword = "securePassword123";

        // WHEN
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void givenMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatched = passwordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "securePassword123";
        String encodedPassword = passwordEncryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean isMatched = passwordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isFalse();
    }
}
