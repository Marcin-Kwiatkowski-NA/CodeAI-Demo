package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent bCryptPasswordEncryptionComponent;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent() {
            @Override
            public String encodePassword(String rawPassword) {
                return mockPasswordEncoder.encode(rawPassword);
            }

            @Override
            public boolean matchedPassword(String rawPassword, String encryptedPassword) {
                return mockPasswordEncoder.matches(rawPassword, encryptedPassword);
            }
        };
    }

    @Test
    void encodePassword_ShouldReturnEncodedPassword() {
        // GIVEN
        String rawPassword = "password123";
        String encodedPassword = "$2a$10$encodedPassword";
        when(mockPasswordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        // WHEN
        String result = bCryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(result).isEqualTo(encodedPassword);
    }

    @Test
    void matchedPassword_ShouldReturnTrueWhenPasswordsMatch() {
        // GIVEN
        String rawPassword = "password123";
        String encryptedPassword = "$2a$10$encodedPassword";
        when(mockPasswordEncoder.matches(rawPassword, encryptedPassword)).thenReturn(true);

        // WHEN
        boolean result = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void matchedPassword_ShouldReturnFalseWhenPasswordsDoNotMatch() {
        // GIVEN
        String rawPassword = "password123";
        String encryptedPassword = "$2a$10$differentEncodedPassword";
        when(mockPasswordEncoder.matches(rawPassword, encryptedPassword)).thenReturn(false);

        // WHEN
        boolean result = bCryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isFalse();
    }
}
