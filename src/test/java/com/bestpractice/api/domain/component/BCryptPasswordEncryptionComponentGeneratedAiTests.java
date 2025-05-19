package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @Test
    void encodePassword_validPassword_returnsEncryptedPassword() {
        // GIVEN: A valid password string
        String rawPassword = "testpassword";

        // WHEN: The encodePassword method is called with the raw password
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN: The encrypted password should be a hashed version of the raw password
        assertNotNull(encryptedPassword);
        // We cannot directly compare the hash, but we can check that it's not the original
        // This is a basic check, more robust testing would involve comparing against a known hash
        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    void matchedPassword_validPasswordAndHash_returnsTrue() {
        // GIVEN: A valid password string and its corresponding hashed version
        String rawPassword = "testpassword";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and the encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The method should return true if the passwords match
        assertTrue(result);
    }

    @Test
    void matchedPassword_invalidPasswordAndHash_returnsFalse() {
        // GIVEN: An invalid password string and its corresponding hashed version
        String rawPassword = "wrongpassword";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and the encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The method should return false if the passwords do not match
        assertFalse(result);
    }
}
