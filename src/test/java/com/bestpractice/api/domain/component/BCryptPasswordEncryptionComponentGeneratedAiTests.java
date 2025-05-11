package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

@ExtendWith(BCryptPasswordEncryptionComponentGeneratedAiTests.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword() {
        // GIVEN a raw password
        String rawPassword = "testPassword123";

        // WHEN the encodePassword method is called
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN the encrypted password should be a hashed version of the raw password
        assert encryptedPassword != null;
    }

    @Test
    void matchedPassword() {
        // GIVEN a raw password and its encrypted counterpart
        String rawPassword = "testPassword123";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN the matchedPassword method is called with the raw password and encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN the result should be true if the passwords match
        assertTrue(result);
    }

    @Test
    void matchedPasswordIncorrectPassword() {
        // GIVEN a raw password and an incorrect encrypted password
        String rawPassword = "testPassword123";
        String incorrectEncryptedPassword = UUID.randomUUID().toString();

        // WHEN the matchedPassword method is called with the raw password and incorrect encrypted password
        boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

        // THEN the result should be false if the passwords do not match
        assertFalse(result);
    }
}
