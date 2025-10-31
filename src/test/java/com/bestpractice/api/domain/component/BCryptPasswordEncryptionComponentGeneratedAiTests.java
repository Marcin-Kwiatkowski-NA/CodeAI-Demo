package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encoded password should not be null and should not equal the raw password
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encoded form
        String rawPassword = "securePass456";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: the match should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encoded password from a different raw password
        String rawPassword = "passwordOne";
        String encodedPassword = encryptionComponent.encodePassword("passwordTwo");

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: the match should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullRawPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: a null raw password and a valid encoded password
        String rawPassword = null;
        String encodedPassword = encryptionComponent.encodePassword("validPassword");

        // WHEN & THEN: matching should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    void givenNullEncodedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a valid raw password and a null encoded password
        String rawPassword = "validPassword";
        String encodedPassword = null;

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN: the match should be false
        assertFalse(matches);
    }
}
