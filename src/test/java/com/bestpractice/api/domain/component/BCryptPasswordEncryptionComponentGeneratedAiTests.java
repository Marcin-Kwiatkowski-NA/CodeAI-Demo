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

import static org.assertj.core.api.Assertions.assertThat;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncodedPassword() {
        // GIVEN
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void givenRawPasswordAndMatchingEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(isMatched).isTrue();
    }

    @Test
    void givenRawPasswordAndNonMatchingEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = bCryptPasswordEncryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean isMatched = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(isMatched).isFalse();
    }
}
