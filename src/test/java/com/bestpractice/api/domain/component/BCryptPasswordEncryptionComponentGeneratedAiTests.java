package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void encodePassword_ShouldReturnEncodedPassword_WhenRawPasswordIsProvided() {
        // GIVEN
        String rawPassword = "TestPassword123";

        // WHEN
        String encodedPassword = passwordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void matchedPassword_ShouldReturnTrue_WhenRawPasswordMatchesEncodedPassword() {
        // GIVEN
        String rawPassword = "TestPassword123";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean isMatched = passwordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isTrue();
    }

    @Test
    void matchedPassword_ShouldReturnFalse_WhenRawPasswordDoesNotMatchEncodedPassword() {
        // GIVEN
        String rawPassword = "TestPassword123";
        String differentRawPassword = "DifferentPassword123";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        // WHEN
        boolean isMatched = passwordEncryptionComponent.matchedPassword(differentRawPassword, encodedPassword);

        // THEN
        assertThat(isMatched).isFalse();
    }
}
