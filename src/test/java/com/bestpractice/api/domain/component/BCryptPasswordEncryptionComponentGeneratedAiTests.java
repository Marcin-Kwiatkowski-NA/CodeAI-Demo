package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private BCryptPasswordEncryptionComponent bcryptPasswordEncryptionComponent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bcryptPasswordEncryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    public void givenValidRawPassword_whenEncodePassword_thenReturnsEncryptedPassword() {
        // GIVEN
        String rawPassword = "password123";

        // WHEN
        String encryptedPassword = bcryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword).contains("$2a$");
    }

    @Test
    public void givenRawPasswordAndCorrectEncryptedPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "password123";
        String encryptedPassword = bcryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatch = bcryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(isMatch).isTrue();
    }

    @Test
    public void givenRawPasswordAndIncorrectEncryptedPassword_whenMatchedPassword_thenReturnsFalse() {
        // GIVEN
        String rawPassword = "password123";
        String correctEncrypted = bcryptPasswordEncryptionComponent.encodePassword(rawPassword);
        String incorrectEncrypted = bcryptPasswordEncryptionComponent.encodePassword("wrongpassword");

        // WHEN
        boolean isMatch = bcryptPasswordEncryptionComponent.matchedPassword(rawPassword, incorrectEncrypted);

        // THEN
        assertThat(isMatch).isFalse();
    }

    @Test
    public void givenNullRawPassword_whenEncodePassword_thenThrowsException() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> bcryptPasswordEncryptionComponent.encodePassword(rawPassword))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("rawPassword");
    }

    @Test
    public void givenNullEncryptedPassword_whenMatchedPassword_thenThrowsException() {
        // GIVEN
        String rawPassword = "password123";
        String encryptedPassword = null;

        // WHEN & THEN
        assertThatThrownBy(() -> bcryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("encryptedPassword");
    }

    @Test
    public void givenEmptyRawPassword_whenEncodePassword_thenReturnsEncryptedPassword() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encryptedPassword = bcryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword).contains("$2a$");
    }

    @Test
    public void givenEmptyRawPasswordAndCorrectEncryptedPassword_whenMatchedPassword_thenReturnsTrue() {
        // GIVEN
        String rawPassword = "";
        String encryptedPassword = bcryptPasswordEncryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean isMatch = bcryptPasswordEncryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN
        assertThat(isMatch).isTrue();
    }
}
