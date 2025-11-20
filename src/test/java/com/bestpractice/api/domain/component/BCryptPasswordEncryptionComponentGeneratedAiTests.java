package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent passwordEncryptionComponent;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncryptionComponent = new BCryptPasswordEncryptionComponent() {
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
    void givenRawPassword_whenEncodePassword_thenReturnsEncodedPassword() {
        // GIVEN
        String rawPassword = "testPassword";
        String encodedPassword = "encodedTestPassword";
        Mockito.when(mockPasswordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        // WHEN
        String result = passwordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(result).isEqualTo(encodedPassword);
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = "encodedTestPassword";
        Mockito.when(mockPasswordEncoder.matches(rawPassword, encryptedPassword)).thenReturn(true);

        // WHEN
        boolean result = passwordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "testPassword";
        String encryptedPassword = "differentEncodedPassword";
        Mockito.when(mockPasswordEncoder.matches(rawPassword, encryptedPassword)).thenReturn(false);

        // WHEN
        boolean result = passwordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(result).isFalse();
    }
}
