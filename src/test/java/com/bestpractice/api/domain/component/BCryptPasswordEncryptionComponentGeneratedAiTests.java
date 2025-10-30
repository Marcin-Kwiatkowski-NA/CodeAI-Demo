package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "testPassword123";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingPasswords_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "securePass";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingPasswords_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "securePass";
        String encodedPassword = encryptionComponent.encodePassword("differentPass");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullValues_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenEmptyRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
    }

    @Test
    void givenMockedPasswordEncoder_whenEncodePassword_thenDelegatesToEncoder() throws Exception {
        // GIVEN
        PasswordEncoder mockEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(mockEncoder.encode("mockPass")).thenReturn("mockEncoded");
        BCryptPasswordEncryptionComponent componentWithMock = new BCryptPasswordEncryptionComponent();
        java.lang.reflect.Field field = BCryptPasswordEncryptionComponent.class.getDeclaredField("passwordEncoder");
        field.setAccessible(true);
        field.set(componentWithMock, mockEncoder);

        // WHEN
        String result = componentWithMock.encodePassword("mockPass");

        // THEN
        assertEquals("mockEncoded", result);
        Mockito.verify(mockEncoder).encode("mockPass");
    }

    @Test
    void givenMockedPasswordEncoder_whenMatchedPassword_thenDelegatesToEncoder() throws Exception {
        // GIVEN
        PasswordEncoder mockEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(mockEncoder.matches("raw", "encoded")).thenReturn(true);
        BCryptPasswordEncryptionComponent componentWithMock = new BCryptPasswordEncryptionComponent();
        java.lang.reflect.Field field = BCryptPasswordEncryptionComponent.class.getDeclaredField("passwordEncoder");
        field.setAccessible(true);
        field.set(componentWithMock, mockEncoder);

        // WHEN
        boolean result = componentWithMock.matchedPassword("raw", "encoded");

        // THEN
        assertThat(result).isTrue();
        Mockito.verify(mockEncoder).matches("raw", "encoded");
    }

    @Test
    void givenMockedPasswordEncoder_whenMatchedPasswordThrowsException_thenPropagateException() throws Exception {
        // GIVEN
        PasswordEncoder mockEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(mockEncoder.matches(Mockito.anyString(), Mockito.anyString()))
               .thenThrow(new IllegalArgumentException("Invalid arguments"));
        BCryptPasswordEncryptionComponent componentWithMock = new BCryptPasswordEncryptionComponent();
        java.lang.reflect.Field field = BCryptPasswordEncryptionComponent.class.getDeclaredField("passwordEncoder");
        field.setAccessible(true);
        field.set(componentWithMock, mockEncoder);

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> componentWithMock.matchedPassword("raw", "encoded"));
    }
}
