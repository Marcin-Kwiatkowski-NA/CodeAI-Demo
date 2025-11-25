package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

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

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncodedPassword() {
        // GIVEN: A raw password
        String rawPassword = "securePassword123";

        // WHEN: Encoding the raw password
        String encodedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN: The encoded password should not be null or empty and should not match the raw password
        assertThat(encodedPassword).isNotNull().isNotEmpty();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void givenRawAndEncodedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: A raw password and its encoded version
        String rawPassword = "securePassword123";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // WHEN: Checking if the raw password matches the encoded password
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: The match result should be true
        assertThat(isMatched).isTrue();
    }

    @Test
    void givenRawAndDifferentEncodedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: A raw password and a different encoded password
        String rawPassword = "securePassword123";
        String differentEncodedPassword = new BCryptPasswordEncoder().encode("differentPassword456");

        // WHEN: Checking if the raw password matches the different encoded password
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, differentEncodedPassword);

        // THEN: The match result should be false
        assertThat(isMatched).isFalse();
    }
}
